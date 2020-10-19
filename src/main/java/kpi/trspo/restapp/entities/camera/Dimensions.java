package kpi.trspo.restapp.entities.camera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class Dimensions implements Comparable<Dimensions> {
    private Integer width;
    private Integer length;
    private Integer depth;

    public Dimensions(String s) {
        this(Integer.parseInt(s.split("x")[0]), Integer.parseInt(s.split("x")[1]),
                Integer.parseInt(s.split("x")[2]));
    }

    @Override
    public String toString() {
        return width + "x" + length + "x" + depth + " см³";
    }

    @Override
    public int compareTo(Dimensions o) {
        return  (this.width <= o.width && this.length <= o.length && this.depth <= o.depth) ? 1 : -1;
    }
}
