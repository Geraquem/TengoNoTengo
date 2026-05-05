package com.mmfsin.tnt.data.repositories

import com.mmfsin.tnt.data.bbdd.SharedPrefs
import com.mmfsin.tnt.domain.interfaces.IConfigRepository
import javax.inject.Inject

class ConfigRepository @Inject constructor(
    val sharedPrefs: SharedPrefs
) : IConfigRepository {

    override fun getAddProductVisible(): Boolean = sharedPrefs.getAddProductVisible()
    override fun updateAddProductVisible(visible: Boolean) = sharedPrefs.setAddProductVisible(visible)

    override fun getActualFilter(): Int = sharedPrefs.getActualFilter()
    override fun updateActualFilter(id: Int) = sharedPrefs.setActualFilter(id)
}