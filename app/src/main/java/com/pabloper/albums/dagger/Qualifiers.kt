package com.pabloper.albums.dagger

import javax.inject.Qualifier

class Qualifiers {

    @Qualifier
    annotation class ForApplication

    @Qualifier
    annotation class ForActivity

}