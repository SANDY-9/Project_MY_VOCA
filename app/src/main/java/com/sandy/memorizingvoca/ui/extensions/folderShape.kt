package com.sandy.memorizingvoca.ui.extensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.ui.theme.Gray30

fun Modifier.folderShape(
    cornerRadius: Float = 20f,
    folderColor: Color = Color.White,
    backgroundColor: Color = Gray30,
    strokeColor: Color = Color.LightGray,
    strokeWidth: Dp = 1.dp,
) = this.then(
    Modifier
        .drawBehind {
            val w = size.width
            val h = size.height
            val r = cornerRadius // 모서리 둥근 정도 (px 단위)

            val path = Path().apply {

                moveTo(r, 0f)

                lineTo(0.37f * w, 0f)

                lineTo(0.47f * w, 0.12f * h)

                lineTo(w - r, 0.12f * h)
                quadraticTo(w, 0.12f * h, w, 0.12f * h + r)


                lineTo(w, h - r)
                quadraticTo(w, h, w - r, h)


                lineTo(r, h)
                quadraticTo(0f, h, 0f, h - r)


                lineTo(0f, r)
                quadraticTo(0f, 0f, r, 0f)

                close()
            }

            inset(vertical = 5f) {
                drawRoundRect(
                    color = backgroundColor,
                    cornerRadius = CornerRadius(r, r),
                )
            }

            drawPath(
                path = path,
                color = folderColor,
                style = Fill,
            )

            drawPath(
                path = path,
                color = strokeColor,
                style = Stroke(width = strokeWidth.toPx())
            )
        }
)

class FolderShape(
    private val cornerRadius: Float = 25f // px 단위
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val w = size.width
        val h = size.height
        val r = cornerRadius

        val path = Path().apply {
            moveTo(r, 0f)

            lineTo(0.37f * w, 0f)
            lineTo(0.47f * w, 0.12f * h)

            lineTo(w - r, 0.12f * h)
            quadraticTo(w, 0.12f * h, w, 0.12f * h + r)

            lineTo(w, h - r)
            quadraticTo(w, h, w - r, h)

            lineTo(r, h)
            quadraticTo(0f, h, 0f, h - r)

            lineTo(0f, r)
            quadraticTo(0f, 0f, r, 0f)

            close()
        }

        return Outline.Generic(path)
    }
}