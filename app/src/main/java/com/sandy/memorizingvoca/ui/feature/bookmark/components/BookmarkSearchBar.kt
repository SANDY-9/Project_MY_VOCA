package com.sandy.memorizingvoca.ui.feature.bookmark.components

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.ui.extensions.addFocusCleaner
import com.sandy.memorizingvoca.ui.extensions.noRippleClickable
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.Pink100
import com.sandy.memorizingvoca.ui.theme.Pink40
import com.sandy.memorizingvoca.ui.theme.Pink80
import com.sandy.memorizingvoca.ui.theme.PyeoginGothic
import com.sandy.memorizingvoca.ui.theme.roundedCornerShape16

@Composable
internal fun BookmarkSearchBar(
    query: String?,
    focusManager: FocusManager,
    focusRequester: FocusRequester,
    onSearchVoca: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var input by rememberSaveable { mutableStateOf(query ?: "") }
    LaunchedEffect(query) {
        input = query ?: ""
    }
    Box(
        modifier = modifier.fillMaxWidth().padding(horizontal = 8.dp),
    ) {
        BookmarkSearchTextField(
            input = input,
            focusManager = focusManager,
            focusRequester = focusRequester,
            onInputChange = { input = it },
            onSearch = { onSearchVoca(input) },
        )
    }
}

@Composable
private fun BookmarkSearchTextField(
    input: String,
    focusManager: FocusManager,
    focusRequester: FocusRequester,
    onInputChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                roundedCornerShape16
            )
            .addFocusCleaner(focusManager)
            .focusRequester(focusRequester),
        value = input,
        onValueChange = onInputChange,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Pink40,
            unfocusedContainerColor = Gray30,
        ),
        textStyle = TextStyle.Default.copy(
            fontFamily = PyeoginGothic,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            color = Color.DarkGray,
        ),
        singleLine = true,
        placeholder = {
            Text(
                text = "단어/뜻 검색, 숫자 입력 시 Day 검색",
                color = Color.Gray,
                fontSize = 16.sp,
            )
        },
        trailingIcon = {
            Icon(
                modifier = modifier.noRippleClickable {
                    focusRequester.requestFocus()
                },
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
                tint = Pink100,
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                if (input.isNotBlank()) {
                    onSearch()
                    focusManager.clearFocus()
                } else {
                    Toast.makeText(context, QUERY_EMPTY_MESSAGE, Toast.LENGTH_SHORT).show()
                }
            },
        ),
    )
}
private const val QUERY_EMPTY_MESSAGE = "검색어를 입력하세요."

@Preview
@Composable
private fun BookmarkSearchBarPreview() {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    MemorizingVocaTheme {
        BookmarkSearchBar(
            query = "Day 1",
            focusManager = focusManager,
            focusRequester = focusRequester,
            onSearchVoca = {},
        )
    }
}
