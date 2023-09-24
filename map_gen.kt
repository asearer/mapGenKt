import kotlin.random.Random

class CellularAutomataMap(width: Int, height: Int, fillProbability: Double, iterations: Int, randomizeInitialMap: Boolean) {
    private var map: Array<Array<Boolean>> = Array(width) { Array(height) { false } }

    init {
        // Initialize the map with random cells or completely randomize it
        if (randomizeInitialMap) {
            for (x in 0 until width) {
                for (y in 0 until height) {
                    map[x][y] = Random.nextDouble() < 0.5
                }
            }
        } else {
            for (x in 0 until width) {
                for (y in 0 until height) {
                    map[x][y] = Random.nextDouble() < fillProbability
                }
            }
        }

        // Perform cellular automata iterations
        for (i in 0 until iterations) {
            val newMap = Array(width) { Array(height) { false } }

            for (x in 0 until width) {
                for (y in 0 until height) {
                    val neighbors = countAliveNeighbors(x, y)

                    // Apply cellular automata rules with some randomness
                    val randomFactor = Random.nextDouble(0.2, 0.8)
                    newMap[x][y] = when {
                        map[x][y] -> neighbors >= 4
                        else -> neighbors >= 5 && Random.nextDouble() < randomFactor
                    }
                }
            }

            // Update the map
            map = newMap
        }
    }

    // ... (rest of the class remains the same)
}

fun main() {
    val width = 50
    val height = 25
    val fillProbability = 0.45
    val iterations = 5
    val randomizeInitialMap = true // Set to true to randomize the initial map completely

    val cellularAutomataMap = CellularAutomataMap(width, height, fillProbability, iterations, randomizeInitialMap)
    val map = cellularAutomataMap.getMap()

    printMap(map)
}
