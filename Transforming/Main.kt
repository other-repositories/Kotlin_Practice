fun main() {

    val figures: Array<Movable>
    val movable: Movable = Rect(0,0,1,1)
    movable.move(1,1)

    val f: Figure = Rect(0,0,1,1)
    val f2: Figure = Circle(3, 2, 2 )

    print("Area $f = ")
    println(f.area())
    print("Area $f2 = ")
    println(f2.area())


    val transformingRect: Transforming = Rect(8,-1,1,2)
    println(transformingRect)

    transformingRect.resize(3)
    println("Resize(2): $transformingRect")

    transformingRect.rotate(RotateDirection.Clockwise, 0, 0)
    println("Rotate(0, 0): $transformingRect")


    val transformingCircle: Transforming = Circle(3, 0, 2)
    println(transformingCircle)

    transformingCircle.resize(2)
    println("Resize(2): $transformingCircle")

    transformingCircle.rotate(RotateDirection.Clockwise, 2, 2)
    println("Rotate(2, 2): $transformingCircle")
}
