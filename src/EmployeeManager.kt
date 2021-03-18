package employee

import java.text.NumberFormat
import java.util.*

fun main() {
    val employeeManager = EmployeeManager()
    employeeManager.start()
}
class EmployeeManager {
    private val employees : MutableList<Employee> = mutableListOf()

    companion object {
        fun getUUID():UUID {
            return UUID.randomUUID()
        }
    }
    fun managementMenu(){
        println("=====기능을 선택해주세요=====")
        println("1: 모든 사원 조회")
        println("2: 사원 추가")
        println("3: 종료")
    }
    //사원 관리 메뉴
    fun start(){
        var menuNumber = 0

        while(menuNumber != 3) {
            managementMenu()
            menuNumber = readLine()?.toInt()!!
            when(menuNumber) {
                1->lookupEmployees()
                2->addEmployee()
                3->break
                else->{}
            }
        }
    }

    //사원 목록 조회
    fun lookupEmployees() {
        println("=====모든 사원의 정보를 출력합니다=====")
        for (i in employees) {
            val formatted = NumberFormat.getCurrencyInstance().format(i.employeeSalary)

            println("사원명 : ${i.employeeName}")
            println("식별번호 : ${i.employeeUUID}")
            println("부서 : ${i.department.departmentName}")
            println("계약형태 : ${i.employeecontract}")
            println("월급 : $formatted")
            println("=====================")
        }
    }
    //사원 추가
    private fun addEmployee() {
        println("추가할 사원의 이름을 입력해주세요")
        val name: String = readLine()!!
        val uuid = getUUID()

        println("추가할 사원의 부서를 입력해주세요")
        println("1: 개발팀")
        println("2: 영업팀")
        println("3: 고객대응팀")
        println("4: 사무직")

        var departmentNumber = readLine()?.toInt()!!
        lateinit var department : Department
        when(departmentNumber) {
            1 -> department = Department.Dev
            2 -> department = Department.Sales
            3 -> department = Department.Cs
            4 -> department = Department.Office
            else -> {}
        }
        println("추가할 사원의 계약형태를 입력해주세요")
        println("1: 계약직")
        println("2: 정규직")
        println("3: 파트타임")
        println("4: 영업직")

        var contractNumber = readLine()?.toInt()!!
        lateinit var employee : Employee
        when(contractNumber) {
            1 -> {
                //계약직
                println("월급을 입력해주세요")
                val salary:Long = readLine()!!.toLong()
                employee = NormalEmployee(name,uuid,department,"계약직", salary)
            }
            2 -> {
                //정규직
                employee = FullTimeEmployee(name,uuid,department, "정규직", 120000000)
            }
            3 -> {
                //파트타임
                println("한달 파트타임 총 시간을 입력해주세요")
                val salary:Long = readLine()!!.toLong()
                employee = PartTimeEmployee(name,uuid,department, "파트타임", salary)
            }
            4 -> {
                //영업직
                println("연봉을 입력해주세요")
                val salary:Long = readLine()!!.toLong()
                employee = SalesEmployee(name,uuid,department, "영업직", salary)
            }
            else -> {}
        }
        println("사원 정보가 입력되었습니다")
        println("사원명 : ${employee.employeeName}")
        println("식별번호 : ${employee.employeeUUID}")
        println("부서 : ${employee.department.departmentName}")
        println("계약형태 : ${employee.employeecontract}")
        println("월급 : ${employee.employeeSalary}")
        employees.add(employee)
    }
}