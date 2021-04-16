import java.lang.Exception
import java.lang.StringBuilder
import kotlin.math.abs
import kotlin.math.round

class Matrix(private val n: Int, private val m: Int){
    private var matrix: Array<Array<Double>> = Array(n){Array(m) {0.0} }

    val rows: Int
        get() = n

    val columns: Int
        get() = m

    constructor(arr: Array<Array<Double>>) : this(arr.size, arr[0].size){
        matrix = arr.clone()
    }

    operator fun plus(other: Matrix) : Matrix{
        if(rows == other.rows && columns == other.columns){
            val result = Matrix(rows, columns)
            for(i in 0 until rows){
                for(j in 0 until columns){
                    result[i][j] = matrix[i][j] + other[i][j]
                }
            }
            return result
        }
        else throw Exception()
    }

    operator fun get(i: Int, j: Int) : Double{
        return matrix[i][j]
    }

    operator fun get(i: Int) : Array<Double>{
        return matrix[i]
    }

    operator fun times(other: Matrix) : Matrix{
        if(m != other.matrix.size)
            throw Exception("Неподходящие размерности!")
        val result = Matrix(n, other.m)
        for (i in matrix.indices){
            for(j in other.matrix[0].indices){
                for(k in other.matrix.indices){
                    result.matrix[i][j] += matrix[i][k] * other.matrix[k][j]
                }
            }
        }
        return result
    }

    operator fun times(scal: Double) : Matrix{
        val result = Matrix(rows, columns)
        for(i in 0 until rows){
            for(j in 0 until columns){
                result[i][j] = this[i][j] * scal
            }
        }
        return result
    }

    operator fun minus(other: Matrix) : Matrix{
        if(rows == other.rows && columns == other.columns){
            val result = Matrix(rows, columns)
            for (i in 0 until rows){
                for(j in 0 until columns){
                    result[i][j] = this[i][j] - other[i][j]
                }
            }
            return result
        }
        else throw Exception("Ошбика 00000000")
    }

    fun scalarTimes(other: Matrix) : Double{
        if(rows == other.rows && columns == other.columns){
            var result = 0.0
            for(i in 0 until rows){
                for(j in 0 until columns){
                    result += this[i][j] * other[i][j]
                }
            }
            return result
        }else throw Exception("не-не-не-не")
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for(i in matrix.indices){
            for(j in matrix[0].indices){
                sb.append("${round(matrix[i][j] * 10000) / 10000}\t\t\t")
            }
            sb.append("\n")
        }
        return sb.toString()
    }

    fun toLowerTriangle() : Matrix{
        val result = Matrix(rows, columns)
        for(i in 0 until rows){
            for (j in 0 until columns){
                if(j <= i){
                    result[i][j] = this[i][j]
                }
            }
        }
        return result
    }

    fun toUpperTriangle() : Matrix{
        val result = Matrix(rows, columns)
        for(i in 0 until rows){
            for (j in 0 until columns){
                if(j >= i){
                    result[i][j] = this[i][j]
                }
            }
        }
        return result
    }

    fun toUpperWithout() : Matrix{
        val result = Matrix(rows, columns)
        for(i in 0 until rows){
            for(j in 0 until columns){
                if(j > i)
                    result[i][j] = this[i][j]
            }
        }
        return result
    }

    fun toLowerWithout() : Matrix{
        val result = Matrix(rows, columns)
        for(i in 0 until rows){
            for(j in 0 until columns){
                if(j < i)
                    result[i][j] = this[i][j]
            }
        }
        return result
    }

    fun firstNorm() : Double{
        var result = 0.0
        var t = 0.0
        for(i in 0 until rows){
            t = 0.0
            for(j in 0 until columns){
                t += abs(this[i][j])
            }
            if(t > result)
                result = t
        }
        return result
    }

    fun secondNorm() : Double{
        var result = 0.0
        var t = 0.0
        for(i in 0 until columns){
            t = 0.0
            for(j in 0 until rows){
                t += abs(this[j][i])
            }
            if(t > result)
                result = t
        }
        return result
    }

    fun transposition() : Matrix{
        val result = Matrix(rows, columns)
        for(i in 0 until rows){
            for(j in 0 until columns){
                result[i][j] = this[j][i]
            }
        }
        return result
    }
}