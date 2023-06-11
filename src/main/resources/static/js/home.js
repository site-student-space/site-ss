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
	
/*-- CarouselSlider --*/
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.carousel');
    var instances = M.Carousel.init(elems, {
    	fullWidth: true,
        indicators: true
	});
});