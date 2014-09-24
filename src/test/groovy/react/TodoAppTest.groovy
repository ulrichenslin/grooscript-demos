package react

/**
 * User: jorgefrancoleza
 * Date: 20/09/14
 */
class TodoAppTest extends GroovyTestCase {

    TodoApp todoApp = new TodoApp()

    void testInit() {
        todoApp.init()
        assert todoApp.actualTodo == ''
        assert todoApp.todos == []
    }

    void testAddTodosSubmit() {
        todoApp.init()
        todoApp.addTodosSubmit()
        assert todoApp.todos == []
        todoApp.actualTodo = 'New'
        todoApp.addTodosSubmit()
        assert todoApp.todos == ['New']
        assert todoApp.actualTodo == ''
    }

    void testActualTodoChange() {
        todoApp.actualTodo = 'initial'
        todoApp.actualTodoChange('final')
        assert todoApp.actualTodo == 'final'
    }

    void testAstAddProperties() {
        assertScript '''
            import org.grooscript.jquery.GQueryImpl

            @react.Component
            class Comp {}

            def comp = new Comp()
            assert comp.gQuery
            assert comp.gQuery instanceof GQueryImpl
            assert comp.selector == null
'''
    }
}
