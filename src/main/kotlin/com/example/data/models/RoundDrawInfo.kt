package com.example.data.models

import com.example.other.Constants.TYPE_CURRENT_ROUND_DRAW_INFO

data class RoundDrawInfo(
    val data: List<String>
): BaseModel(TYPE_CURRENT_ROUND_DRAW_INFO)
