





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
					<div class="title_ueser">
						<img src="img/index_icon_09.png" class="users"/>
						<div>
							<p>神悦萌</p>
							<span>2016-11-25 14:20</span>
						</div>
						<em>
							<img src="img/huo_03.jpg"/>
							<i>热门</i>
						</em>
					</div>
					<p class="font">公交上给闺蜜发消息，跟她说我身边坐了一个大帅
哥，不知该怎么搭讪。她说语音聊吧，结果我打开
语音后，二货突然大嗓子喊道：旁边的那位帅哥，
我闺蜜看上你了，可以留个电话么？尼玛，我这张
老脸啊......</p>
					<div class="icon_list">
						<a href="#">
							<img src="img/index_icon_14.png"/>
							<p>1388</p>
						</a>
						<a href="#">
							<img src="img/index_icon_17.png"/>
							<p>30</p>
						</a>
						<a href="#">
							<img src="img/index_icon_19.png"/>
							<p>分享</p>
						</a>
						<a href="#" class="pl">
							<img src="img/index_icon_21.png"/>
							<p>1278</p>
						</a>
					</div>
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