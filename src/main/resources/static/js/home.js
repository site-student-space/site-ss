/*-- Gaveta de apps - Redes sociais --*/
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.fixed-action-btn');
    var instances = M.FloatingActionButton.init(elems, {
      direction: 'top',
      hoverEnabled: false
    });
  });


/*-- Slider --*/
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.slider');
    var instances = M.Slider.init(elems);
    
  	window.next = function() {
    var elem = document.querySelector('.slider');
    var inst = M.Slider.getInstance(elem);
    inst.next(1);
    };
    window.prev = function() {
    var elem = document.querySelector('.slider');
    var inst = M.Slider.getInstance(elem);
    inst.prev(1);
    };
  });
	
	
/*-- Carousel de membros --*/
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.carousel');
    var instances = M.Carousel.init(elems, {
		indicators: true,
	});
	// custom function for autoplaying 
	let indicatorItems = document.querySelectorAll('.carousel .indicator-item'),
	slideTime = 5000,
	activeClass = "active";

	setInterval(() => {
		indicatorItems.forEach(el => {
			if (el.classList.contains(activeClass)) {
        		sib = el.nextElementSibling;
		        if (sib == null) {
		          indicatorItems[0].click();
		        } else {
		          sib.click()
		        }
      		}
    	});
	}, slideTime);
});
