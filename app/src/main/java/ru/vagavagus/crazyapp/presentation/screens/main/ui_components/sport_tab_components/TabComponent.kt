package ru.vagavagus.crazyapp.presentation.screens.main.ui_components.sport_tab_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vagavagus.crazyapp.presentation.screens.main.components.common.TabItem
import ru.vagavagus.crazyapp.ui.theme.CrazyAppTheme
import ru.vagavagus.crazyapp.ui.theme.UnselectedColor
import ru.vagavagus.crazyapp.ui.theme.Yellow

@Composable
fun TabComponent(
    modifier: Modifier = Modifier,
    tabItem: TabItem,
    selected: Boolean,
    onSelected: () -> Unit
) {
    Column(
        modifier = modifier
            .height(60.dp)
            .padding(bottom = 3.dp)
            .clickable { onSelected() }
            .then(
                if (selected) Modifier.background(Yellow) else Modifier
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(1.dp, Alignment.CenterVertically)
    ) {
        Spacer(
            modifier = Modifier.then(
                if(selected)
                    Modifier
                        .size(12.dp, 3.dp)
                        .clip(shape = RoundedCornerShape(bottomStartPercent = 30, bottomEndPercent = 30))
                        .background(Color.Black)
                else
                    Modifier
                        .size(12.dp, 3.dp)
            )
        )
        Spacer(modifier = Modifier.height(3.dp))
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = tabItem.icon),
            contentDescription = null,
            tint = if(selected) LocalContentColor.current else UnselectedColor
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = tabItem.name,
            style = TextStyle(
                fontSize = 12.sp,
                color = if(selected) Color.Black else UnselectedColor,
            )
        )
    }
}

@Preview
@Composable
private fun TabComponentPreview() {
    CrazyAppTheme {
        TabComponent(
            tabItem = TabItem.Football,
            selected = false,
            onSelected = {}
        )
    }
}