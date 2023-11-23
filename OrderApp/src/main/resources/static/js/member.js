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

//  회원가입 페이지로 이동
$(document).ready(function () {
  $("#signInBtn").on("click", function (e) {
    e.preventDefault();

    window.location.href = "/signInCheckID";
  });
});

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
        window.location.href = "/termsOfUse";
      }
    },
    error: function () {
      alert("서버와 통신 중 오류가 발생했습니다.");
    },
  });
}

// 전체 동의 체크박스
var checkAll = document.getElementById("checkAll");
//  하부 체크박스
var otherCheckboxes = document.querySelectorAll(".otherCheckbox");
// 전체 동의 체크박스의 상태가 변경되었을 때 실행되는 함수
function toggleCheckboxes() {
  // 전체 동의 체크박스의 상태에 따라 다른 체크박스들의 상태 변경
  otherCheckboxes.forEach(function (checkbox) {
    checkbox.checked = checkAll.checked;
  });
}

// 전체 동의 체크박스에 이벤트 리스너 추가
checkAll.addEventListener("change", toggleCheckboxes);

// 멤버십 약관 페이지로 이동
$(document).ready(function () {
  $("#membershipTOU").on("click", function (e) {
    e.preventDefault();

    window.location.href = "/membershipTOU";
  });
});

//  개인정보 처리 방침 페이지로 이동
$(document).ready(function () {
  $("#userTOU").on("click", function (e) {
    e.preventDefault();

    window.location.href = "/userTOU";
  });
});

document.getElementById("signInForm").addEventListener("click", function () {
  // (필수) 체크박스의 상태 확인
  var membershipTOUChecked = document.getElementById(
    "membershipTOUCheckbox"
  ).checked;
  var userTOUChecked = document.getElementById(
  "userTOUCheckbox"
  ).checked;

  // (필수) 체크박스가 체크되었는지 확인하고, 체크되지 않았다면 경고 메시지를 표시
  if (!membershipTOUChecked || !userTOUChecked) {
    alert("이용약관에 동의해야 합니다.");
    return; // 다음으로 진행하지 않음
  }

  // (필수) 체크박스가 체크되었으면 다음 페이지로 이동
  window.location.href = "/signInForm"; // 회원가입 상세 입력 페이지
});

//  이용자 아이디 출력 페이지로 이동
$(document).ready(function () {
  $("#findSubmitBtn").on("click", function (e) {
    e.preventDefault();

    window.location.href = "/resultId";
  });
});

//  이용자 비밀번호 출력 페이지로 이동
$(document).ready(function () {
  $("#findSubmitBtnPw").on("click", function (e) {
    e.preventDefault();

    window.location.href = "/resultPw";
  });
});
