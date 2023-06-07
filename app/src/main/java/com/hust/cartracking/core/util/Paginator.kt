package com.hust.cartracking.core.util

/********************
 * @Author: Tiiee
 * @Date: 5/25/2023
 * @Time: 9:58 PM
 ********************/

interface Paginator<T> {
 suspend fun loadNextItems()
}