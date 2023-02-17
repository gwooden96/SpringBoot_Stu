const btns = document.querySelectorAll('.delete');

//delete 클래스가 여러개 있을 경우 반복문을 사용해줘야 버튼들이 기능을 한다.
btns.forEach(function(btn) {
	
		btn.addEventListener('click', function() {
		
			if(confirm('정말로 삭제하시겠습니까?')) {
				location.href = this.dataset.uri;
			}
		
		});
	
});


