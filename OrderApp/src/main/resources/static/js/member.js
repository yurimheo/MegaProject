//  로고 클릭시 로그인 페이지로 이동
$(document).ready(function () {
  $("#megaLogo").on("click", function (e) {
    e.preventDefault();

    window.location.href = "/login";
  });
});

// 1. 로그인 페이지
// 로그인 버튼 클릭시 로그인 액션 함수 발동
function loginAction() {
  let idValue = document.getElementById("inputMemberId").value;
  let pwValue = document.getElementById("inputMemberPw").value;
  console.log("아이디: " + idValue);
  console.log("비밀번호: " + pwValue);

  let params = {
    loginId: idValue,
    loginPw: pwValue,
  };

  console.log(JSON.stringify(params));

  fetch("/loginAction", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(params),
  })
    .then((response) => {
      console.log("response:" + response);
      console.log("response:" + JSON.stringify(response));
      return response.json();
    }) //HTTP 응답
    .then((json) => {
      //{ status: "ok", result: 5 }
      console.log("json:" + json);
      console.log("response:" + JSON.stringify(json));

      if (json.result == 1) {
        //로그인 성공
        //다음페이지로 이동
        window.location.href = "/main";
      } else {
        //로그인 실패
        alert("로그인 실패입니다.");
      }
    }) //실제 데이타
    .catch((error) => {
      console.log(error);
    });
}

// 회원가입 페이지
//  회원가입 페이지로 이동
$(document).ready(function () {
  $("#signInBtn").on("click", function (e) {
    e.preventDefault();

    window.location.href = "/signInCheckID";
  });
});

// 회원가입 1. 중복 ID 값 체크
// 회원가입시 아이디 중복 확인
// 아이디 중복 확인 함수
function checkIdAction() {
  // 입력된 아이디 가져오기
  var username = $("#inputMemberId").val();

  // 입력된 아이디가 비어있는지 확인
  if (!username) {
    alert("아이디를 입력해주세요!");
    return;
  }

  // 아이디 중복 확인을 위한 AJAX 요청
  $.ajax({
    type: "POST",
    url: "/checkIdAction",
    contentType: "application/json",
    dataType: "json", // 서버에서 받을 데이터 타입을 명시
    data: JSON.stringify({ loginId: username }),
    success: function (response) {
      if (response.result === 0) {
        alert("아이디가 이미 존재합니다. 다른 아이디를 입력해주세요.");
      } else {
        // 중복이 아닌 경우에 한하여  상세 입력 페이지로 이동하면서 아이디 전달함
        window.location.href =
          "/termsOfUse?username=" + encodeURIComponent(username);
      }
    },
    error: function () {
      alert("서버와 통신 중 오류가 발생했습니다.");
    },
  });
}
