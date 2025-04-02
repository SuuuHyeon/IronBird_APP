package com.example.greetingcard.presentation.view.plan.detailplan

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DetailPlanScreen(planId: Int?) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (planId != null) {
            Text("플랜 id: $planId")
        } else {
            Text("Plan not found")
        }
    }
}