 //  로그인 후 메인 페이지로 이동 + 관리자인 경우 관리자 페이지로 이동
      $(document).ready(function () {
        $("#mainBtn").on("click", function (e) {
          e.preventDefault();

          var enteredUsername = $("#inputMemberId").val();
          var isAdmin = enteredUsername.toLowerCase() === "admin";

          // 관리자 여부 확인
          if (isAdmin) {
            window.location.href = "admin.html"; // 관리자 페이지로 이동
          } else {
            window.location.href = "main.html"; // 이용자 페이지로 이동
          }
        });
      });

      //  회원가입 페이지로 이동
      $(document).ready(function () {
        $("#signInBtn").on("click", function (e) {
          e.preventDefault();

          window.location.href = "signInCheckID.html";
        });
      });

      //  로고 클릭시 로그인 페이지로 이동
      $(document).ready(function () {
        $("#megaLogo").on("click", function (e) {
          e.preventDefault();

          window.location.href = "loginForm.html";
        });
      });

      // 로그인 버튼 클릭시 로그인 액션 함수 발동
      function loginAction() {
        let idValue = document.getElementById("inputMemberId").value;
        let pwValue = document.getElementById("inputMemberPw").value;
        console.log("아이디: " + idValue);
        console.log("비밀번호: " + pwValue);

        let params = {
          loginId : idValue,
          loginPw : pwValue,
        };

        console.log(JSON.stringify(params));

        fetch("/loginAction", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(params),
        })
        .then((response) => {
          console.log("response:"+response);
          console.log("response:"+JSON.stringify(response));
          return response.json();
        }) //HTTP 응답
        .then((json) => {
          //{ status: "ok", result: 5 }
          console.log("json:" + json);
          console.log("response:"+JSON.stringify(json));

          if( json.result == 1 ){
              //로그인 성공
              //다음페이지로 이동
              window.location.href = "/main";
          }else{
              //로그인 실패
              alert('로그인 실패입니다.');
          }

        }) //실제 데이타
          .catch((error) => {
          console.log(error);
        });
      }

      // 회원가입 클릭시 회원가입 폼으로 이동
      function signInForm(){
          window.location.href = "/signInForm";
      }