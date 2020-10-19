package kpi.trspo.restapp.entities.camera;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "camera_lens")

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public final class CameraLens {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "focal_length")
    @NonNull
    private Integer focalLength;

    @Enumerated(EnumType.STRING)
    @Column(name = "lens_type")
    @NonNull
    private LensType lensType;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cameraLens", orphanRemoval = true)
    @JoinColumn(name = "camera_id")
    private Camera camera;
}
