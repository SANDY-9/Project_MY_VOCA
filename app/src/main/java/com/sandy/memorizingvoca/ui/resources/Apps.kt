package com.sandy.memorizingvoca.ui.resources

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.Unit

public val Icons.Rounded.Apps: ImageVector
    get() {
        if (_apps != null) {
            return _apps!!
        }
        val _apps = Builder(
            name =
                "Apps", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 960.0f, viewportHeight = 960.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF1f1f1f)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(243.6f, 792.0f)
                quadToRelative(-31.1f, 0.0f, -53.35f, -22.15f)
                reflectiveQuadTo(168.0f, 716.6f)
                quadToRelative(0.0f, -31.1f, 22.15f, -53.35f)
                reflectiveQuadTo(243.4f, 641.0f)
                quadToRelative(31.1f, 0.0f, 53.35f, 22.15f)
                reflectiveQuadTo(319.0f, 716.4f)
                quadToRelative(0.0f, 31.1f, -22.15f, 53.35f)
                reflectiveQuadTo(243.6f, 792.0f)
                close()
                moveTo(480.1f, 792.0f)
                quadToRelative(-31.1f, 0.0f, -53.35f, -22.15f)
                reflectiveQuadTo(404.5f, 716.6f)
                quadToRelative(0.0f, -31.1f, 22.15f, -53.35f)
                reflectiveQuadTo(479.9f, 641.0f)
                quadToRelative(31.1f, 0.0f, 53.35f, 22.15f)
                reflectiveQuadToRelative(22.25f, 53.25f)
                quadToRelative(0.0f, 31.1f, -22.15f, 53.35f)
                reflectiveQuadTo(480.1f, 792.0f)
                close()
                moveTo(716.6f, 792.0f)
                quadToRelative(-31.1f, 0.0f, -53.35f, -22.15f)
                reflectiveQuadTo(641.0f, 716.6f)
                quadToRelative(0.0f, -31.1f, 22.15f, -53.35f)
                reflectiveQuadTo(716.4f, 641.0f)
                quadToRelative(31.1f, 0.0f, 53.35f, 22.15f)
                reflectiveQuadTo(792.0f, 716.4f)
                quadToRelative(0.0f, 31.1f, -22.15f, 53.35f)
                reflectiveQuadTo(716.6f, 792.0f)
                close()
                moveTo(243.6f, 555.5f)
                quadToRelative(-31.1f, 0.0f, -53.35f, -22.15f)
                reflectiveQuadTo(168.0f, 480.1f)
                quadToRelative(0.0f, -31.1f, 22.15f, -53.35f)
                reflectiveQuadToRelative(53.25f, -22.25f)
                quadToRelative(31.1f, 0.0f, 53.35f, 22.15f)
                reflectiveQuadTo(319.0f, 479.9f)
                quadToRelative(0.0f, 31.1f, -22.15f, 53.35f)
                reflectiveQuadTo(243.6f, 555.5f)
                close()
                moveTo(480.1f, 555.5f)
                quadToRelative(-31.1f, 0.0f, -53.35f, -22.15f)
                reflectiveQuadTo(404.5f, 480.1f)
                quadToRelative(0.0f, -31.1f, 22.15f, -53.35f)
                reflectiveQuadToRelative(53.25f, -22.25f)
                quadToRelative(31.1f, 0.0f, 53.35f, 22.15f)
                reflectiveQuadToRelative(22.25f, 53.25f)
                quadToRelative(0.0f, 31.1f, -22.15f, 53.35f)
                reflectiveQuadTo(480.1f, 555.5f)
                close()
                moveTo(716.6f, 555.5f)
                quadToRelative(-31.1f, 0.0f, -53.35f, -22.15f)
                reflectiveQuadTo(641.0f, 480.1f)
                quadToRelative(0.0f, -31.1f, 22.15f, -53.35f)
                reflectiveQuadToRelative(53.25f, -22.25f)
                quadToRelative(31.1f, 0.0f, 53.35f, 22.15f)
                reflectiveQuadTo(792.0f, 479.9f)
                quadToRelative(0.0f, 31.1f, -22.15f, 53.35f)
                reflectiveQuadTo(716.6f, 555.5f)
                close()
                moveTo(243.6f, 319.0f)
                quadToRelative(-31.1f, 0.0f, -53.35f, -22.15f)
                reflectiveQuadTo(168.0f, 243.6f)
                quadToRelative(0.0f, -31.1f, 22.15f, -53.35f)
                reflectiveQuadTo(243.4f, 168.0f)
                quadToRelative(31.1f, 0.0f, 53.35f, 22.15f)
                reflectiveQuadTo(319.0f, 243.4f)
                quadToRelative(0.0f, 31.1f, -22.15f, 53.35f)
                reflectiveQuadTo(243.6f, 319.0f)
                close()
                moveTo(480.1f, 319.0f)
                quadToRelative(-31.1f, 0.0f, -53.35f, -22.15f)
                reflectiveQuadTo(404.5f, 243.6f)
                quadToRelative(0.0f, -31.1f, 22.15f, -53.35f)
                reflectiveQuadTo(479.9f, 168.0f)
                quadToRelative(31.1f, 0.0f, 53.35f, 22.15f)
                reflectiveQuadToRelative(22.25f, 53.25f)
                quadToRelative(0.0f, 31.1f, -22.15f, 53.35f)
                reflectiveQuadTo(480.1f, 319.0f)
                close()
                moveTo(716.6f, 319.0f)
                quadToRelative(-31.1f, 0.0f, -53.35f, -22.15f)
                reflectiveQuadTo(641.0f, 243.6f)
                quadToRelative(0.0f, -31.1f, 22.15f, -53.35f)
                reflectiveQuadTo(716.4f, 168.0f)
                quadToRelative(31.1f, 0.0f, 53.35f, 22.15f)
                reflectiveQuadTo(792.0f, 243.4f)
                quadToRelative(0.0f, 31.1f, -22.15f, 53.35f)
                reflectiveQuadTo(716.6f, 319.0f)
                close()
            }
        }
            .build()
        return _apps!!
    }

private var _apps: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(
            imageVector = Icons.Rounded.Apps,
            contentDescription = "",
        )
    }
}
