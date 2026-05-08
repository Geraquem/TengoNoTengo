@file:OptIn(ExperimentalMaterial3Api::class)

package com.mmfsin.tnt.presentation.productdetail.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mmfsin.tnt.R
import com.mmfsin.tnt.domain.models.Category
import com.mmfsin.tnt.domain.models.CategoryType.Companion.getCategories
import com.mmfsin.tnt.domain.models.CategoryType.Companion.getCategoryById
import com.mmfsin.tnt.presentation.core.components.SwitchFavorite
import com.mmfsin.tnt.presentation.core.components.SwitchHaveIt
import com.mmfsin.tnt.presentation.core.theme.BlueMedium
import com.mmfsin.tnt.presentation.core.theme.GrayHard
import com.mmfsin.tnt.presentation.core.theme.GreenMedium
import com.mmfsin.tnt.presentation.core.theme.RedMedium
import com.mmfsin.tnt.presentation.core.theme.White
import com.mmfsin.tnt.presentation.core.theme.YellowHard
import com.mmfsin.tnt.presentation.core.theme.YellowLight

@Preview(showBackground = true)
@Composable
fun ProductDetailDataPV() {
    ProductDetailData(
        "Soja Texturizada",
        {},
        "",
        {},
        "",
        {},
        true,
        {},
        false,
        {},
        getCategories(),
        category = getCategoryById(15),
        true,
        {},
        {},
        true,
        {},
        {}
    )
}

@Composable
fun ProductDetailData(
    name: String,
    onNameChanged: (String) -> Unit,

    whereToFind: String?,
    onWhereToFindChanged: (String) -> Unit,

    info: String?,
    onInfoChanged: (String) -> Unit,

    haveIt: Boolean,
    updateHaveIt: (Boolean) -> Unit,

    favorite: Boolean,
    updateFavorite: (Boolean) -> Unit,

    categories: List<Category>,
    category: Category,
    categoriesState: Boolean,
    updateCategory: (Int) -> Unit,
    updateCategoriesState: () -> Unit,

    deleteButtonVisible: Boolean,
    onDeleteClick: () -> Unit = {},
    onConfirmClick: () -> Unit
) {
    CompositionLocalProvider(LocalOverscrollFactory provides null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 20.dp).padding(bottom = 80.dp)
        ) {
            /****************************************************** NAME */
            TitleText(R.string.product_detail_name)
            MainEditableText(
                name, { onNameChanged(it) },
                isFavorite = favorite
            )

            Spacer(Modifier.height(16.dp))


            /****************************************************** HAVE IT */
            SwitchBox(
                text = R.string.product_detail_have_it,
                checked = haveIt,
                updateState = { updateHaveIt(it) },
                isFav = false
            )

            Spacer(Modifier.height(8.dp))

            /****************************************************** FAVORITE */
            SwitchBox(
                text = R.string.product_detail_favorite,
                checked = favorite,
                updateState = { updateFavorite(it) },
                isFav = true
            )

            Spacer(Modifier.height(8.dp))

            /****************************************************** CATEGORY */

            ExposedDropdownMenuBox(
                modifier = Modifier.fillMaxWidth(),
                expanded = categoriesState,
                onExpandedChange = { updateCategoriesState() }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp))
                        .background(White).clickable(onClick = { updateCategoriesState() })
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    val openedDropMenu by animateFloatAsState(
                        targetValue = if (categoriesState) -1f else 1f,
                        label = ""
                    )

                    category.icon?.let { icon ->
                        Icon(
                            painterResource(icon), null,
                            tint = category.color
                        )
                        Spacer(Modifier.width(6.dp))
                    }
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(category.name),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Icon(
                        painterResource(R.drawable.ic_arrow_down), null,
                        tint = GrayHard,
                        modifier = Modifier.graphicsLayer { scaleY = openedDropMenu }
                    )
                }
                ExposedDropdownMenu(
                    modifier = Modifier.clip(RoundedCornerShape(16.dp)),
                    expanded = categoriesState,
                    onDismissRequest = { updateCategoriesState() },
                ) {
                    categories.forEach { category ->
                        CategoryItem(
                            category,
                            onClick = { id -> updateCategory(id) }
                        )
                    }
                }
            }

            Spacer(Modifier.height(32.dp))
            /****************************************************** INFO */
            TitleText(R.string.product_detail_where)
            EditableText(text = whereToFind ?: "", { onWhereToFindChanged(it) })

            Spacer(Modifier.height(20.dp))

            /**************************************************** WHERE TO FIND IT */
            TitleText(R.string.product_detail_info)
            EditableText(text = info ?: "", { onInfoChanged(it) }, maxLines = 20)

            Spacer(Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                if (deleteButtonVisible) {
                    TextButton(onClick = { onDeleteClick() }) {
                        Text(
                            stringResource(R.string.product_detail_delete),
                            style = MaterialTheme.typography.bodyLarge,
                            color = RedMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(Modifier.width(24.dp))
                }
                TextButton(onClick = { onConfirmClick() }) {
                    Text(
                        stringResource(R.string.product_detail_save),
                        style = MaterialTheme.typography.bodyLarge,
                        color = BlueMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}


@Composable
fun TitleText(text: Int) {
    Text(
        text = stringResource(text),
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.SemiBold,
        color = GrayHard,
        modifier = Modifier.fillMaxWidth().padding(start = 8.dp, bottom = 4.dp)
    )
}

@Composable
fun MainEditableText(
    text: String,
    onValueChange: (String) -> Unit,
    isFavorite: Boolean
) {
    BasicTextField(
        value = text, onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp))
            .background(if (isFavorite) YellowLight else White)
            .padding(horizontal = 12.dp, vertical = 16.dp),
        textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            capitalization = KeyboardCapitalization.Sentences
        ),
    )
}

@Composable
fun EditableText(
    text: String,
    onValueChange: (String) -> Unit,
    maxLines: Int = 1,
) {
    BasicTextField(
        value = text, onValueChange = { onValueChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(White)
            .padding(12.dp),
        textStyle = MaterialTheme.typography.bodyLarge,
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(
            imeAction = if (maxLines == 1) ImeAction.Done else ImeAction.None,
            capitalization = KeyboardCapitalization.Sentences
        ),
    )
}

@Composable
fun SwitchBox(text: Int, checked: Boolean, updateState: (Boolean) -> Unit, isFav: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp))
            .background(White).padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            stringResource(text),
            style = MaterialTheme.typography.bodyLarge,
            color =
                if (isFav) {
                    if (checked) YellowHard else GrayHard
                } else {
                    if (checked) GreenMedium else GrayHard
                },
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.SemiBold
        )
        if (isFav) SwitchFavorite(checked, onCheckedChange = { updateState(it) })
        else SwitchHaveIt(checked, onCheckedChange = { updateState(it) })
    }
}

@Composable
fun CategoryItem(category: Category, onClick: (Int) -> Unit) {
    DropdownMenuItem(
        modifier = Modifier.fillMaxWidth(),
        text = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                category.icon?.let { icon ->
                    Icon(
                        painterResource(icon), stringResource(category.name),
                        tint = category.color
                    )
                    Spacer(Modifier.width(8.dp))
                }
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(category.name),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        },
        onClick = { onClick(category.id) })
}