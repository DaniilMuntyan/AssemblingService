package kpi.trspo.restapp.entities.camera;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "camera_backs")

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public final class CameraBack {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "matrix_check")
    private Boolean matrixCheck = false;

    @Convert(converter = DimensionsConverter.class)
    @NonNull
    private Dimensions dimensions;

    @Column(name = "resolution")
    @NonNull
    private Integer resolution;
    
    @Column(name = "color_depth")
    @NonNull
    private Integer colorDepth;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cameraBack", orphanRemoval = true)
    @JoinColumn(name = "camera_id")
    private Camera camera;
}
