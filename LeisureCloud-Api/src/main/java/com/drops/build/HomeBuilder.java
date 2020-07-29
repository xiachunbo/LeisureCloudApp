package com.drops.build;


class HomeBuilder extends AbsBuilder{

    private MyHome mMyHome = new MyHome();

    @Override
    MyHome build() {
        return mMyHome;
    }

    @Override
    HomeBuilder processOpertion1(String t) {
        mMyHome.setDoor(t);
        return this;
    }

    @Override
    HomeBuilder processOpertion2(String t) {
        mMyHome.setDoor(t);
        return this;
    }

    @Override
    HomeBuilder processOpertion3(String t) {
        mMyHome.setDoor(t);
        return this;
    }

    @Override
    HomeBuilder processOpertion4(String t) {
        mMyHome.setDoor(t);
        return this;
    }

    @Override
    HomeBuilder processOpertion5(String t) {
        mMyHome.setDoor(t);
        return this;
    }
}
