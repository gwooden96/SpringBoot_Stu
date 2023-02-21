const btns = document.querySelectorAll('.delete');

//delete 클래스가 여러개 있을 경우 반복문을 사용해줘야 버튼들이 기능을 한다.
btns.forEach(function(btn) {
	
		btn.addEventListener('click', function() {
		
			if(confirm('정말로 삭제하시겠습니까?')) {
				location.href = this.dataset.uri;
			}
		
		});
	
});


const recommendBtns = document.querySelectorAll('.recommend');

recommendBtns.forEach(function(btn) {
	
	btn.addEventListener('click', function() {
		
		if(confirm('추천하시겠습니까?')) {
			location.href = this.dataset.uri;
		}
		
	});
	
});



const pageBtns = document.querySelectorAll('.page-link');
const searchBtn = document.querySelector('#btn_search');

/*let page = document.querySelector('#page');
let searchForm = document.querySelector('#searchForm');
let kw = document.querySelector('#kw');
let search_kw = document.querySelector('#search_kw');*/


pageBtns.forEach(function(btn) {
	
	btn.addEventListener('click', function() {
	
		document.querySelector('#page').value = this.dataset.page;
		document.querySelector('#searchForm').submit();
		
		});
		
	
});


	searchBtn.addEventListener('click', function() {
	
		document.querySelector('#kw').value = document.querySelector('#search_kw').value;
		document.querySelector('#page').value = 0;
		document.querySelector('#searchForm').submit();
	
		});



