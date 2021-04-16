import kotlin.math.pow

fun main(){

}

fun approximationPower(q: Double, norm: Double, epsilon: Double) : Int{
    var k = 0
    var approx: Double = epsilon + 1
    while (approx >= epsilon){
        k++
        approx = (q.pow(k) / (1 - q)) * norm
    }
    return k
}

fun getApproximation(k: Int, F: Matrix, f: Matrix) : Matrix{
    var prev = Matrix(f.rows, f.columns)
    var result = Matrix(f.rows, f.columns)
    var c = 0
    while(c < k){
        c++
        result = F * prev + f
        prev = result
    }
    println(c)
    return result
}

fun getFirstApproximation(F: Matrix, f: Matrix) : Matrix{
    val x_0 = Matrix(f.rows, f.columns)
    return F * x_0 + f
}

fun getZeroR(x_0: Matrix, A: Matrix, b: Matrix) : Matrix{
    return b - A * x_0
}

fun thirdNorm(vector: Matrix) : Double{
    var result = 0.0
    for (i in 0 until vector.rows){
        for(j in 0 until vector.columns){
            result += vector[i][j].pow(2)
        }
    }
    return result.pow(0.5)
}

fun getGradApproximationPower(A: Matrix, y: Matrix, m: Double, M: Double, epsilon: Double) : Int{
    var k = 0
    val x0 = Matrix(y.rows, y.columns)
    val r0 = getZeroR(x0, A, y)
    val norm = thirdNorm(r0)
    var approximation = epsilon + 1
    while (approximation >= epsilon){
        k++
        approximation = (norm / m) * ((M-m)/(M+m)).pow(k)
    }
    return k
}

fun gradDescend(k: Int, A: Matrix, b: Matrix) : Matrix{
    var result = Matrix(b.rows, b.columns)
    var x0 = Matrix(b.rows, b.columns)
    var r0 = getZeroR(x0, A, b)
    var c = 0
    var tau: Double
    while(c < k){
        x0 = result
        r0 = b - A * x0
        tau = r0.scalarTimes(r0) / r0.scalarTimes(A * r0)
        result = x0 + r0 * tau
        c++
    }
    return result
}