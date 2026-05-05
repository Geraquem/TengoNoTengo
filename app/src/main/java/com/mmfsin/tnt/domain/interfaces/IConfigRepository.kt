package com.mmfsin.tnt.domain.interfaces

interface IConfigRepository {
    fun getAddProductVisible(): Boolean
    fun updateAddProductVisible(visible: Boolean)

    fun getActualFilter(): Int
    fun updateActualFilter(id: Int)
}