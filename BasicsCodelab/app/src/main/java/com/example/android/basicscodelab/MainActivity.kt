package com.example.android.basicscodelab

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import com.example.android.basicscodelab.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicsCodelabTheme {
                MyApp(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(value = "", onValueChange = {},
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        }
    )
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawableRes: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(drawableRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(text),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 24.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(item.drawable, item.text)

        }
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawableRes: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ) {
            Image(

                painter = painterResource(drawableRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.wrapContentHeight()
            )
        }
    }
}

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(text = stringResource(id = title),
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp))
        content()
    }
}

@Composable
fun HomeScreen(modifier: Modifier=Modifier){
    Column (
        modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())){
        SearchBar()
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favorite_collections){
            FavoriteCollectionsGrid()
        }
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, widthDp = 320, heightDp = 320)
@Composable
fun PreviewHomeScreen() {
    BasicsCodelabTheme {
        HomeScreen()
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun PreviewHomeSectionPreview() {
    BasicsCodelabTheme {
        HomeSection(R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
}
@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(
                drawableRes = item.drawable,
                text = item.text,
                Modifier.height(80.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewFavoriteCollectionsGrid() {
    BasicsCodelabTheme {
        FavoriteCollectionsGrid()
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewFavoriteCollectionCard() {
    BasicsCodelabTheme {
        FavoriteCollectionCard(
            text = R.string.fc2_nature_meditations,
            drawableRes = R.drawable.fc2_nature_meditations,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewAlignBodyElement() {
    BasicsCodelabTheme {
        AlignYourBodyRow()
    }
}

@Preview
@Composable
fun PreviewSearchBar() {
    BasicsCodelabTheme {
        SearchBar()
    }
}


@Composable
fun MyApp(modifier: Modifier = Modifier, names: List<String> = List(1000) { "$it" }) {
    var shouldShowOnboarding by remember {
        mutableStateOf(true)
    }
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Greetings(modifier)
        }
    }


}

@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcom to the basics codeLab!")
        Button(
            onClick = onContinueClicked,
            modifier = Modifier.padding(vertical = 24.dp)
        ) {
            Text("Continue")
        }
    }
}

@Composable
fun Greetings(modifier: Modifier = Modifier, names: List<String> = List(1000) { "$it" }) {
//    Column(modifier = modifier.padding(vertical = 4.dp)) {
//        for (name in names) {
//            Greeting(name = name)
//        }
//    }

    LazyColumn(modifier = modifier.padding(4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
//    var expanded by rememberSaveable { mutableStateOf(false) }
//    val extraPadding by animateDpAsState(
//        if (expanded) 48.dp else 0.dp,
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessLow
//        ), label = ""
//    )
//
//    Surface(
//        color = MaterialTheme.colorScheme.primary,
//        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
//    ) {
//        Row(modifier = Modifier.padding(24.dp)) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(1f)
//                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
//            ) {
//                Text(text = "Hello ")
//                Text(
//                    text = name, style = MaterialTheme.typography.headlineMedium.copy(
//                        fontWeight = FontWeight.ExtraBold,
//                    )
//                )
//            }
//            IconButton(
//                onClick = {
//                    expanded = !expanded
//                }) {
//                Icon(
//                    bitmap = Icons.Filled.ExpandLess,
//                    contentDescription = if (expanded) "Show less" else "Show more"
//                )
//                Text(text = if (expanded) "Show less" else "Show more")
//            }
//        }
//
//    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}

@Composable
fun CardContent(name: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }
//    val extraPadding by animateDpAsState(
//        if (expanded) 48.dp else 0.dp,
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessLow
//        ), label = ""
//    )

//    Surface(
//        color = MaterialTheme.colorScheme.primary,
//        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
//    ) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(12.dp)

        ) {
            Text(text = "Hello ")
            Text(
                text = name, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                )
            )

            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                        "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }

//            IconButton(onClick = { expanded = !expanded }) {
//                Icon(
//                    ImageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
//                    contentDescription = if (expanded) stringResource(R.string.show_less) else stringResource(R.string.show_more),
//                    tint = MaterialTheme.colorScheme.onSurface
//                    )
//            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) stringResource(R.string.show_less) else stringResource(
                    R.string.show_more
                )
            )
        }
    }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    BasicsCodelabTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}


@Preview(
    showBackground = true, widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "GreetingPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    BasicsCodelabTheme {
        Greetings()
    }
}

@Preview(showBackground = true, widthDp = 320, showSystemUi = true, wallpaper = Wallpapers.NONE)
@Composable
fun MyAppPreview() {
    BasicsCodelabTheme {
        MyApp(Modifier.fillMaxWidth())
    }
}