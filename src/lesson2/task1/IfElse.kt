@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import java.lang.Math.abs

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -Math.sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    val y3 = Math.max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -Math.sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String = when (age%100) {
    in 5..20 -> "$age лет"
    else -> when (age%10) {
        1 -> "$age год"
        else -> when (age%10) {
            in 2..4 -> "$age года"
            else -> "$age лет"
        }
    }
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val halfway = ((t1 * v1 + t2 * v2 + t3 * v3) / 2)
    return when {
        (t1 * v1 >= halfway) -> halfway / v1
        else -> when {
            ((t1 * v1 + t2 * v2) >= halfway) -> (halfway - t1 * v1) / v2 + t1
            else -> (halfway - t1 * v1 - t2 * v2) / v3 + t1 +t2
            }
        }
    }

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    val a = (kingX - rookX1)
    val b = (kingY - rookY1)
    val a1 = (kingX - rookX2)
    val b1 = (kingY - rookY2)
    return when {
        (a != 0) && (a1 != 0) && (b != 0) && (b1 != 0) -> 0
        ((a == 0) || (b == 0)) && (a1 != 0) && (b1 != 0) -> 1
        ((a1 == 0) || (b1 == 0)) && (a != 0) && (b != 0) -> 2
        else -> 3
    }
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    val a: Int = (kingX - rookX)
    val b: Int = (kingY - rookY)
    val a1: Int = abs(kingX - bishopX) - abs(kingY - bishopY)
    return when {
        (a != 0) && (a1 != 0) && (b != 0) -> 0
        ((a == 0) || (b == 0)) && (a1 != 0) -> 1
        (a1 == 0) && (a != 0) && (b != 0) -> 2
        else -> 3
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    if ((a + b < c) || (b + c < a) || (a + c < b)) return -1
    if ((a == b) && (b == c)) return 0
    if (((a == b) && (a > c)) || ((a == c) && (a > c)) || ((b == c) && (b > a))) return 0
    if (((a == b) && (a < c)) || ((a == c) && (a < c)) || ((b == c) && (b < a))) return 2
    val maxside: Double = a
    val fside: Double = b
    val sside: Double = c
    if (b>maxside) {
        maxside.equals(b)
        fside.equals(a)
        sside.equals(c)
    }
    if (c>maxside) {
        maxside.equals(c)
        fside.equals(a)
        sside.equals(b)
    }
    return when {
        maxside * maxside == fside * fside + sside * sside -> 1
        maxside * maxside < fside * fside + sside * sside -> 2
        maxside * maxside > fside * fside + sside * sside -> 0
        else -> -1
        }
    }

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int = when {
    ((a > c && a > d) || (c > a && c > b)) -> -1
    else -> when {
        (b == c) -> 0
        else -> when {
            (c > a) && (d < b) -> (d -c)
            else -> when {
                (a > c) && (b < d) -> (b-a)
                else -> when {
                    (a > c) -> (abs(d) - abs(a))
                    else -> when {
                    (c > a) -> (abs(b) - abs(c))
                        else -> -1
                    }
                }
            }
        }
    }
}