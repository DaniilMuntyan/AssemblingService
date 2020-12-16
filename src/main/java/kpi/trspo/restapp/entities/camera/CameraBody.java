package kpi.trspo.restapp.entities.camera;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="camera_bodies")

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public final class CameraBody {
    @Id
    @GeneratedValue
    private UUID id;

    @Convert(converter = DimensionsConverter.class)
    @NonNull
    private Dimensions dimensions;

    @Column(name="color")
    @NonNull
    private String color;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cameraBody", orphanRemoval = true)
    @JoinColumn(name="camera_id")
    private Camera camera;
}
