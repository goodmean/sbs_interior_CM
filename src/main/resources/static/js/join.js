let CHECK_STATUS = false;
let LOGIN_ID_STATUS = false;
let NICKNAME_STATUS = false;

async function checkDupleLoginId(){

    let inputLoginId = document.querySelector("#loginId");
    let loginId = inputLoginId.value;

    await fetch("http://localhost:8085/members/check/id?loginId=" + loginId)
    .then(

        (response) => {
            return response.json();
        }
    )
    .then(
        (data) => {

            let idCheck = data;

            if( idCheck.status || loginId === "" ){

                LOGIN_ID_STATUS = false;
                alert("이미 사용중인 아이디 입니다.")

            }else{

                LOGIN_ID_STATUS = true;
                alert("사용할 수 있는 아이디 입니다.")

            }
        }
    )
    .catch(
        (error) => {
            console.log(error);
        }
    )
}

async function checkDupleNickname(){

    let inputNickname = document.querySelector("#nickname");
    let nickname = inputNickname.value;

    await fetch("http://localhost:8085/members/check/nickname?nickname=" + nickname)
    .then(
        (response) => {

            return response.json();
        }

    )
    .then(
        (data) => {

            let nicknameCheck = data;

            if(nicknameCheck.status || nickname===""){
                NICKNAME_STATUS = false;
                alert("가입하실 수 없는 닉네임 입니다.")
            }else{
                NICKNAME_STATUS = true;
                alert("가입하실 수 있는 닉네임 입니다.")
            }


        }

    )
    .catch(
        (error) => {
            console.log(error);
        }

    )

}

function checkStatus(){

    if(LOGIN_ID_STATUS){
        CHECK_STATUS = true;
    }else{
        CHECK_STATUS = false;
    }

    if(!CHECK_STATUS){
        alert("아이디 중복확인을 해주세요.");
        return false;
    }

}