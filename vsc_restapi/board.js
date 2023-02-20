const url = "http://localhost:8089/board";

window.onload = function() {

$.get(url)
  .done(function(board) {

    //요청 성공시 실행할 코드
    console.log(board);
    console.log(board[0].title);

    board.forEach((data) => {

      let form = 
      ` <tr>
      <td>${data.no}</td>
      <td>
          <a href="/detail.html?no=${data.no}">${data.title}</a>
      </td>
      <td>${data.id}</td>
      <td>${data.postdate}</td>
      <td>${data.visitcount}</td>
        </tr>`;
        
        $('tbody').append(form);

    });

  });
}