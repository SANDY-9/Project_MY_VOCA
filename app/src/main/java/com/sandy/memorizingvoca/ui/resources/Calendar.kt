package com.sandy.memorizingvoca.ui.resources

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
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

public val Icons.Rounded.Calendar: ImageVector
    get() {
        if (_calendar != null) {
            return _calendar!!
        }
        _calendar = Builder(name = "Calendar", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF1f1f1f)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(200.0f, 880.0f)
                quadToRelative(-33.0f, 0.0f, -56.5f, -23.5f)
                reflectiveQuadTo(120.0f, 800.0f)
                verticalLineToRelative(-560.0f)
                quadToRelative(0.0f, -33.0f, 23.5f, -56.5f)
                reflectiveQuadTo(200.0f, 160.0f)
                horizontalLineToRelative(40.0f)
                verticalLineToRelative(-40.0f)
                quadToRelative(0.0f, -17.0f, 11.5f, -28.5f)
                reflectiveQuadTo(280.0f, 80.0f)
                quadToRelative(17.0f, 0.0f, 28.5f, 11.5f)
                reflectiveQuadTo(320.0f, 120.0f)
                verticalLineToRelative(40.0f)
                horizontalLineToRelative(320.0f)
                verticalLineToRelative(-40.0f)
                quadToRelative(0.0f, -17.0f, 11.5f, -28.5f)
                reflectiveQuadTo(680.0f, 80.0f)
                quadToRelative(17.0f, 0.0f, 28.5f, 11.5f)
                reflectiveQuadTo(720.0f, 120.0f)
                verticalLineToRelative(40.0f)
                horizontalLineToRelative(40.0f)
                quadToRelative(33.0f, 0.0f, 56.5f, 23.5f)
                reflectiveQuadTo(840.0f, 240.0f)
                verticalLineToRelative(255.0f)
                quadToRelative(0.0f, 17.0f, -11.5f, 28.5f)
                reflectiveQuadTo(800.0f, 535.0f)
                quadToRelative(-17.0f, 0.0f, -28.5f, -11.5f)
                reflectiveQuadTo(760.0f, 495.0f)
                verticalLineToRelative(-95.0f)
                lineTo(200.0f, 400.0f)
                verticalLineToRelative(400.0f)
                horizontalLineToRelative(249.0f)
                quadToRelative(17.0f, 0.0f, 28.0f, 11.5f)
                reflectiveQuadToRelative(11.0f, 28.5f)
                quadToRelative(0.0f, 17.0f, -11.5f, 28.5f)
                reflectiveQuadTo(448.0f, 880.0f)
                lineTo(200.0f, 880.0f)
                close()
                moveTo(661.0f, 787.0f)
                lineTo(803.0f, 645.0f)
                quadToRelative(12.0f, -12.0f, 28.0f, -12.0f)
                reflectiveQuadToRelative(28.0f, 12.0f)
                quadToRelative(12.0f, 12.0f, 12.0f, 28.5f)
                reflectiveQuadTo(859.0f, 702.0f)
                lineTo(690.0f, 872.0f)
                quadToRelative(-12.0f, 12.0f, -28.5f, 12.0f)
                reflectiveQuadTo(633.0f, 872.0f)
                lineToRelative(-85.0f, -85.0f)
                quadToRelative(-12.0f, -12.0f, -12.0f, -28.5f)
                reflectiveQuadToRelative(12.0f, -28.5f)
                quadToRelative(12.0f, -12.0f, 28.5f, -12.0f)
                reflectiveQuadToRelative(28.5f, 12.0f)
                lineToRelative(56.0f, 57.0f)
                close()
            }
        }
        .build()
        return _calendar!!
    }

private var _calendar: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = Icons.Rounded.Calendar, contentDescription = "")
    }
}
