package com.mmfsin.tnt.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.tnt.R
import com.mmfsin.tnt.domain.models.HomeType
import com.mmfsin.tnt.domain.usecases.getItems
import com.mmfsin.tnt.presentation.core.components.Toolbar
import com.mmfsin.tnt.presentation.core.theme.GrayLight
import com.mmfsin.tnt.presentation.core.theme.White

@Preview(showBackground = true)
@Composable
fun HomeScreenPV() {
    HomeContent(HomeStates(items = getItems())) {}
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateTo: (HomeType) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeContent(uiState) { type -> navigateTo(type) }
}

@Composable
fun HomeContent(
    uiState: HomeStates,
    navigateTo: (HomeType) -> Unit
) {
    Scaffold(
        topBar = { Toolbar(text = R.string.app_name, iconBackVisible = false) }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding).background(GrayLight)) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp).background(GrayLight)
            ) {

                Box(
                    modifier = Modifier.fillMaxWidth()
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(16.dp),
                            clip = false
                        )
                        .clip(RoundedCornerShape(16.dp))
                        .clickable(onClick = {navigateTo(HomeType.MY_PRODUCTS)})
                        .background(White)
                        .padding(horizontal = 16.dp, vertical = 42.dp)
                ) {
                    Text("Mis cositas")
                }

                Spacer(Modifier.height(24.dp))

                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    MyBox(
                        modifier = Modifier.weight(1f),
                        text = R.string.app_name,
                        onClick = {})

                    Spacer(Modifier.width(16.dp))

                    MyBox(
                        modifier = Modifier.weight(1f),
                        text = R.string.app_name,
                        onClick = {})
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    MyBox(
                        modifier = Modifier.weight(1f),
                        text = R.string.app_name,
                        onClick = {})

                    Spacer(Modifier.width(16.dp))

                    MyBox(
                        modifier = Modifier.weight(1f),
                        text = R.string.app_name,
                        onClick = {})
                }
            }
        }
    }
}

@Composable
fun MyBox(
    modifier: Modifier,
    text: Int,
    onClick: () -> Unit
) {
    Box(
        modifier.shadow(
            elevation = 6.dp,
            shape = RoundedCornerShape(16.dp),
            clip = false
        )
            .clip(RoundedCornerShape(16.dp))
            .background(White)
            .clickable(onClick = { onClick() })
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(painterResource(R.drawable.ic_arrow_back), stringResource(text))
            Spacer(Modifier.height(8.dp))
            Text(text = stringResource(text), style = MaterialTheme.typography.bodyLarge)
        }
    }
}