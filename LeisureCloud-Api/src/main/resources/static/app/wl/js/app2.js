





function compheight() {
    var scrollTop = $(this).scrollTop();
    var scrollHeight = $(document).height();
    var windowHeight = $(this).height();
    if(scrollTop + windowHeight == scrollHeight){
        return true
    }
    else {
        return false
    }
}

class Lmz{
    constructor(){
    }

    move(e){
        if (compheight()) {
            var y=e.changedTouches[0].clientY;
            $(".lmz-last-tip").height(y/10);
                $(".lmz-todo").append(`
                <li>
                <img src="img/wul_1.jpg" alt=""/>
                <span>
                    <h3>教你如何谈恋爱</h3>
                    <p>定期为您推荐花式恋爱小技巧，让您快速了解女人心</p>
                </span>
                <a href="#">进入</a>
            </li>
            `);
        }
    }


    init(){
        window.addEventListener("touchmove",this.move);
    }

}
//
// $(window).on("touchmove",function (e) {
//     var x=e.changedTouches[0].clientX;
//     var y=e.changedTouches[0].clientY;
// });