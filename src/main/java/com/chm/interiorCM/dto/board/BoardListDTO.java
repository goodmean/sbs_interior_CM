package com.chm.interiorCM.dto.board;

import com.chm.interiorCM.domain.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardListDTO {

	private Long boardId;

	private String name;
	private String detail;

	public BoardListDTO(Board board){

		this.boardId = board.getId();
		this.name = board.getName();
		this.detail = board.getDetail();

	}

}
