package com.mmfsin.tnt.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollFactory
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.tnt.R
import com.mmfsin.tnt.domain.models.HomeItem
import com.mmfsin.tnt.domain.models.HomeTypeClassification.Companion.getHomeItems
import com.mmfsin.tnt.domain.models.HomeTypeClassification.MY_PRODUCTS
import com.mmfsin.tnt.presentation.core.components.Toolbar
import com.mmfsin.tnt.presentation.core.theme.Black
import com.mmfsin.tnt.presentation.core.theme.GrayLight
import com.mmfsin.tnt.presentation.core.theme.RedHard
import com.mmfsin.tnt.presentation.core.theme.White
import com.mmfsin.tnt.presentation.home.components.MainBox
import com.mmfsin.tnt.presentation.home.components.ProductsBox

@Preview(showBackground = true)
@Composable
fun HomeScreenPV() {
    HomeContent(HomeStates(items = getHomeItems()), {}, {})
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToHomeClassification: (Int) -> Unit,
    navigateToDefaultProducts: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeContent(
        uiState = uiState,
        navigateToHomeClassification = { type -> navigateToHomeClassification(type) },
        navigateToDefaultProducts = { navigateToDefaultProducts() }
    )
}

@Composable
fun HomeContent(
    uiState: HomeStates,
    navigateToHomeClassification: (Int) -> Unit,
    navigateToDefaultProducts: () -> Unit
) {
    Scaffold(
        topBar = { Toolbar(text = R.string.app_complete_name, iconBackVisible = false, mainTitle = true) }
    ) { innerPadding ->
        CompositionLocalProvider(LocalOverscrollFactory provides null) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
                    .background(GrayLight)
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
            ) {

                item {
                    Spacer(Modifier.height(24.dp))
                    MainBox(navigateTo = { navigateToHomeClassification(MY_PRODUCTS.id) })
                    Spacer(Modifier.height(16.dp))
                }

                item {
                    val chunks = uiState.items.chunked(2)

                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        chunks.forEach { rowItems ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                rowItems.forEach { item ->
                                    Box(modifier = Modifier.weight(1f)) {
                                        HomeBox(item) { navigateToHomeClassification(item.id) }
                                    }
                                }
                            }
                        }
                    }
                }

                item {
                    Spacer(Modifier.height(16.dp))
                    ProductsBox(navigateTo = { navigateToDefaultProducts() })
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun HomeBox(
    item: HomeItem,
    onClick: () -> Unit
) {
    Box(
        Modifier.height(170.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false
            )
            .clip(RoundedCornerShape(16.dp))
            .background(White)
            .clickable(onClick = { onClick() }),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(item.pngBackground), null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(modifier = Modifier.fillMaxSize().alpha(0.35f).background(Black))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier.size(60.dp),
                contentAlignment = Alignment.Center
            ) {
                item.icon { onClick() }
            }
            Text(
                text = stringResource(item.title),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.clip(RoundedCornerShape(16.dp))
                    .background(White)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}