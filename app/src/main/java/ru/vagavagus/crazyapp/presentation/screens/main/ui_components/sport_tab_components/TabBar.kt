package ru.vagavagus.crazyapp.presentation.screens.main.ui_components.sport_tab_components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.vagavagus.crazyapp.presentation.screens.main.components.common.TabItem

@Composable
fun TabBar(
    selectedTab: TabItem,
    onTabChange: (TabItem) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    ) {
        TabComponent(
            modifier = Modifier.weight(.25f),
            tabItem = TabItem.Football,
            selected = selectedTab is TabItem.Football,
            onSelected = { onTabChange(TabItem.Football) }
        )
        TabComponent(
            modifier = Modifier.weight(.25f),
            tabItem = TabItem.CyberFootball,
            selected = selectedTab is TabItem.CyberFootball,
            onSelected = { onTabChange(TabItem.CyberFootball) }
        )
        TabComponent(
            modifier = Modifier.weight(.25f),
            tabItem = TabItem.Basketball,
            selected = selectedTab is TabItem.Basketball,
            onSelected = { onTabChange(TabItem.Basketball) }
        )
        TabComponent(
            modifier = Modifier.weight(.25f),
            tabItem = TabItem.Tennis,
            selected = selectedTab is TabItem.Tennis,
            onSelected = { onTabChange(TabItem.Tennis) }
        )
    }
}