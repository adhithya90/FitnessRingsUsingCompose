package com.example.tryoutshapes

import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import android.util.Half.toFloat
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tryoutshapes.ui.theme.*
import kotlinx.coroutines.isActive
import java.util.function.DoublePredicate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TryOutShapesTheme {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    FitnessCircles()
                }
            }
        }
    }
}

@Composable
fun FitnessCircles() {
    val startAngle = -90f
    val angle1 = remember {
        Animatable(initialValue = 0f)
    }
    val angle2 = remember {
        Animatable(initialValue = 0f)
    }
    val angle3 = remember {
        Animatable(initialValue = 0f)
    }

    val angles = listOf(angle1, angle2, angle3)

    val density = LocalDensity.current.density
    val boxSize = 300f
    val strokeWidth = 90f
    val biggestCircle = (boxSize * density) - (strokeWidth / 2 * density)

    Canvas(
        modifier = Modifier
            .size(Dp(boxSize))
    ) {
        val offset1 = strokeWidth / 2
        val size1 = biggestCircle
        drawArc(
            brush = Brush.linearGradient(listOf(Ring1Color1, Ring1Color2)),
            startAngle,
            angle1.value,
            false,
            topLeft = Offset(offset1, offset1),
            size = Size(size1, size1),
            style = Stroke(strokeWidth, 0f, cap = StrokeCap.Round)
        )


        val offset2 = (strokeWidth / 2) + strokeWidth
        val size2 = biggestCircle - (strokeWidth * 2)

        drawArc(
            Brush.linearGradient(listOf(Ring2Color1, Ring2Color2)),
            startAngle,
            angle2.value,
            false,
            topLeft = Offset(offset2, offset2),
            size = Size(size2, size2),
            style = Stroke(strokeWidth, 0f, cap = StrokeCap.Round)
        )

        val offset3 = (strokeWidth * 2) + (strokeWidth / 2)
        val size3 = biggestCircle - (strokeWidth * 4)


        drawArc(
            Brush.linearGradient(listOf(Ring3Color1, Ring3Color2)),
            startAngle,
            angle3.value,
            false,
            topLeft = Offset(offset3, offset3),
            size = Size(size3, size3),
            style = Stroke(strokeWidth, 0f, cap = StrokeCap.Round)
        )

    }

    LaunchedEffect(key1 = true) {
        while (isActive) {
            for (item in angles) {
                item.animateTo(
                    targetValue = 300f,
                    animationSpec = tween(
                        durationMillis = 3000,
                        easing = LinearOutSlowInEasing,
                    )
                )
            }
        }
    }

}


@Preview(showBackground = true, backgroundColor = 0xff000000)
@Composable
fun DefaultPreview() {
    TryOutShapesTheme {
        FitnessCircles()
    }
}