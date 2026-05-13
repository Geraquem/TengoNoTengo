package com.mmfsin.tnt.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.tnt.R
import com.mmfsin.tnt.domain.models.HomeItem
import com.mmfsin.tnt.domain.models.HomeTypeClassification.Companion.getHomeItems
import com.mmfsin.tnt.domain.models.HomeTypeClassification.MY_PRODUCTS
import com.mmfsin.tnt.presentation.core.components.Toolbar
import com.mmfsin.tnt.presentation.core.theme.Black
import com.mmfsin.tnt.presentation.core.theme.GrayLight
import com.mmfsin.tnt.presentation.core.theme.White

@Preview(showBackground = true)
@Composable
fun HomeScreenPV() {
    HomeContent(HomeStates(items = getHomeItems())) {}
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToHomeClassification: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeContent(uiState) { type -> navigateToHomeClassification(type) }
}

@Composable
fun HomeContent(
    uiState: HomeStates,
    navigateToHomeClassification: (Int) -> Unit
) {
    Scaffold(
        topBar = { Toolbar(text = R.string.app_name, iconBackVisible = false) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
                .padding(horizontal = 16.dp).background(GrayLight)
        ) {

            Spacer(Modifier.height(24.dp))

            MainHomeBox { navigateToHomeClassification(MY_PRODUCTS.id) }

            Spacer(Modifier.height(12.dp))

            LazyVerticalGrid(
                contentPadding = PaddingValues(vertical = 12.dp),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(uiState.items) { item -> HomeBox(item) { navigateToHomeClassification(item.id) } }
            }
        }
    }
}

@Composable
fun MainHomeBox(navigateTo: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(220.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false
            )
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = { navigateTo() })
            .background(White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(MY_PRODUCTS.pngBackground), null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(modifier = Modifier.fillMaxSize().alpha(0.20f).background(Black))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.weight(1f))
            Text(
                text = stringResource(R.string.home_box_my_products),
                style = MaterialTheme.typography.titleLarge,
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = stringResource(R.string.home_box_my_products_all_i_have),
                style = MaterialTheme.typography.bodySmall,
                color = White,
            )
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun HomeBox(
    item: HomeItem,
    onClick: () -> Unit
) {
    Box(
        Modifier.height(170.dp).shadow(
            elevation = 6.dp,
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
                    .padding(horizontal = 8.dp)
            )
        }
    }
}