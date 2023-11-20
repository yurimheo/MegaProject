//  툴팁 리셋
document.addEventListener("DOMContentLoaded", function () {
  var tooltipTriggerList = [].slice.call(
    document.querySelectorAll('[data-bs-toggle="tooltip"]')
  );
  var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl);
  });
});

// 회원관리 CSS 시작

// 회원상세조회 페이지로 이동
$(document).ready(function () {
  $(".member-detail").on("click", function (e) {
    e.preventDefault();

    window.location.href = "memberDetail.html";
  });
});

// 회원관리 페이지로 이동
$(document).ready(function () {
  $(".md-submit").on("click", function (e) {
    e.preventDefault();

    window.location.href = "memberManagement.html";
  });
});
// 회원관리 CSS 끝
