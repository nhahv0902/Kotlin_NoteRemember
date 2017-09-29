package com.nhahv.noteremember.data

/**
 * Created by nhahv0902 on 9/29/17.
 */
class Notebook() : Comparable<Notebook> {
    var id: Long = System.currentTimeMillis()
    var key: String? = null
    var title: String? = null
    var content: String? = null
    var place: String? = null
    var date: Long? = System.currentTimeMillis()
    var time: String? = null
    var picture: ArrayList<String> = ArrayList()

    override fun compareTo(other: Notebook): Int {
        return (id - other.id).toInt()
    }
}
