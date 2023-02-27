package hr.project.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Goran
 */
class PositionAdapter extends XmlAdapter<Integer, Position> {

    @Override
    public Position unmarshal(Integer id) throws Exception {
        return Position.from(id);
    }

    @Override
    public Integer marshal(Position position) throws Exception {
        return Position.from(position);
    }

}
