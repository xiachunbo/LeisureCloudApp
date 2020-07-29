package com.drops.build;


class HomeProvider {
    /**
     * 售楼处将用户需求交给建造者，由建造者去创建房屋对象
     *
     * @param level 档次
     * */
    static MyHome commandBuilder(String level) {
        HomeBuilder homeBuilder = new HomeBuilder();
        return homeBuilder
                .processOpertion1("操作1")
                .processOpertion2("操作2")
                .processOpertion3("操作3")
                .processOpertion4("操作4")
                .processOpertion5("操作5")
                .build();
    }

    public static void main(String[] args) {
        System.out.println(HomeProvider.commandBuilder("1").toString());
    }
}
