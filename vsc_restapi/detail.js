const urlSearch = new URLSearchParams(location.search);
const no = urlSearch.get('no');
let url = "http://localhost:8089/detail?no=" + no;

console.log(no);

window.onload = function() {

  $.get(url)
    .done(function(data){
        
      $('.title').text(data.title);
      $('.id').text(data.id);
      $('.content').text(data.content);
      
      data.commentList.forEach(comment => {

        let form =
        `
        <div class="mb-3 border d-flex">
          <div class="p-2 col-2">@${comment.id}</div>
          <div class="p-2 col-9">${comment.content}</div>
          <button class="btn-cmt-del btn btn-danger col-1" data-code="${comment.code}">x</button>
        </div>
        `;

      $('.cmt').append(form);

      });

    });

};

// 댓글 삭제 버튼 이벤트
//html에서 만든 코드가 아니기 때문에 이런식으로 해줘야 클릭 이벤트가 활성화 된다.
$(document).on('click', '.btn-cmt-del', function(e) {

    //이벤트가 발생된 타겟에 dataset에 code를 빼오는 기능
    let code = e.target.dataset.code;

    if(confirm('.정말로 삭제하시겠습니까?')) {

    $.ajax({
        url: "http://localhost:8089/delete/" + code,
        method: "delete",
        success: function() {
            $(e.target).parent().remove()
            alert('삭제 완료')
        }
    });
  }

});


// 댓글 추가 이벤트
$('.submit').on('click', function(e){

  e.preventDefault();

  const commInfo = $('.commForm').serialize();
  console.log(commInfo);

  let id = $('.commForm .form-control').eq(0);
  let content = $('.commForm .form-control').eq(1);
  
  console.log(id.val());
  console.log(content.val());

  $.post("http://localhost:8089/insert?no=" + no, commInfo)
    .done(function(){
      alert('댓글 작성 완료');

      let form =
        `
        <div class="mb-3 border d-flex">
          <div class="p-2 col-2">${id.val()}</div>
          <div class="p-2">${content.val()}</div>
        </div>
        `;

      $('.cmt').append(form);

      id.val('');
      content.val('');

    });
});