package countries

import org.grooscript.jquery.GQuery
import org.grooscript.jquery.GQueryImpl
import org.grooscript.jquery.GQueryList
import spock.lang.Specification

/**
 * User: jorgefrancoleza
 * Date: 19/10/14
 */
class CountriesPresenterSpec extends Specification {

    def 'start presenter'() {
        when:
        presenter.start()

        then:
        presenter.countries.size() == 1
        1 * sigma.addNode([id: 'AFG', label: 'Afghanistan', x: 65.0, y: 33.0, color: presenter.purpleColor])
        1 * sigma.addEdge('AFGAFG', 'AFG', 'AFG')
        1 * sigma.refresh()
        1 * gQueryList.methodMissing('html', ['1 found. '])
        0 * _
    }

    def 'on change to empty country'() {
        when:
        presenter.countries = presenter.loadCountries()
        presenter.onChangeCountry('')

        then:
        1 * sigma.applyToNodes(_)
        1 * gQueryList.methodMissing('html', ['1 found. '])
        1 * sigma.moveCamaraTo(0, 0, 1)
        1 * sigma.refresh()
        0 * _
    }

    def 'on change to non existing country'() {
        when:
        presenter.countries = presenter.loadCountries()
        presenter.onChangeCountry('NULL')

        then:
        1 * sigma.applyToNodes(_)
        1 * gQueryList.methodMissing('html', ['0 found. '])
        1 * sigma.moveCamaraTo(0, 0, 1)
        1 * sigma.refresh()
        0 * _
    }

    private CustomSigma sigma = Mock()
    private gQueryList = Mock(GQueryList)
    private GQuery gQuery = Stub(GQueryImpl) {
        call('#searchResult') >> gQueryList
    }
    CountriesPresenter presenter = new CountriesPresenter(customSigma: sigma, gQuery: gQuery)
}
