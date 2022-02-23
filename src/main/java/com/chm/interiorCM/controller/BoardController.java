package com.chm.interiorCM.controller;

import com.chm.interiorCM.domain.Board;
import com.chm.interiorCM.domain.Member;
import com.chm.interiorCM.dto.article.ArticleListDTO;
import com.chm.interiorCM.dto.board.BoardDTO;
import com.chm.interiorCM.dto.board.BoardModifyForm;
import com.chm.interiorCM.dto.board.BoardSaveForm;
import com.chm.interiorCM.service.BoardService;
import com.chm.interiorCM.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    // 게시판 리스트
    @GetMapping("/boards")
    public String showBoardList(Model model){

        List<Board> boardList = boardService.findAll();

        model.addAttribute("boardList", boardList);

        return "adm/board/list";
    }

    @GetMapping("/boards/{id}") // http://localhost:8085/boards/id?page=1&searchKeyword=제목
    public String showBoardDetail(@PathVariable(name = "id")Long id, Model model, @RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "searchKeyword", defaultValue = "") String searchKeyword){

        int size = 10;

        try{
            BoardDTO boardDetail = boardService.getBoardDetail(id);

            List<ArticleListDTO> articleListDTO = boardDetail.getArticleListDTO();

            List<ArticleListDTO> store = new ArrayList<>();

            for( ArticleListDTO listDTO : articleListDTO ){

                if( listDTO.getTitle().contains(searchKeyword) ){
                    store.add(listDTO);
                }
            }

            if( store.size() != 0 ){
                articleListDTO = store;
            }

            Collections.reverse(articleListDTO);
            // 0, 10, 20 ...
            int startIndex = (page - 1) * size;
            // 9, 19, 29 ... -> 15 -> 1.5(총 게시글 개수 / 10(size)) -> 올림
            int lastIndex = ((page -1) * size) + 9;

            int lastPage = (int)Math.ceil(articleListDTO.size()/(double)size);

            if( page == lastPage ){  // ?page=2 == 2

                lastIndex = articleListDTO.size(); // 15  [0,1,2,3,4,5,6,7,,,14]

            }else if( page > lastPage ){  // ?page=100 == 2

                return "redirect:/";

            }else{  // ?page=1 == 2
                lastIndex += 1;
            }

            // 페이지 자르기
            List<ArticleListDTO> articlePage = articleListDTO.subList(startIndex, lastIndex); // [0, 10] -> 0,1,2,3,4,5,6,7,8,9

            if( !searchKeyword.equals("") && store.size() == 0 ){
                articlePage = store;
            }

            model.addAttribute("board", boardDetail);
            model.addAttribute("articles", articlePage);
            model.addAttribute("maxPage", lastPage);
            model.addAttribute("currentPage", page);
        }catch (Exception e){
            return "redirect:/";
        }

        return "adm/board/detail";

    }

}
