function bannerslide(lunboshijian,jiangeshijian) {
	var nowimg = 0;
	$(".banner .banner-list li:first").clone().appendTo(".banner .banner-list");

	var timer =	setInterval(slideFunc,jiangeshijian);

	function slideFunc(){
		if(!$(".banner .banner-list").is(":animated")){
			if(nowimg < $(".banner .banner-list li").length - 2){
				nowimg++;
				$(".banner .banner-list").animate({"left":-100 * nowimg + "%"},lunboshijian);
			}else{
				nowimg = 0;
				$(".banner .banner-list").animate({"left":-100 *($(".banner .banner-list li").length - 1) + "%"},lunboshijian,function(){
					$(".banner .banner-list").css("left",0);
				})
			}
			$(".banner .imgnav li").eq(nowimg).addClass(" active").siblings().removeClass(" active");
		}
	}
	$(".banner .imgnav li").click(
		function(){
			$(".banner .banner-list").stop(true); 
			nowimg = $(this).index();
			$(".banner .banner-list").animate({"left":-100*nowimg+ "%"},lunboshijian);
			$(".banner .imgnav li").eq(nowimg).addClass(" active").siblings().removeClass(" active");
		}
	);
}	