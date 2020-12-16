package kpi.trspo.restapp.entities.camera;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "cameras")

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public final class Camera {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID camera_id;

    private String innerInfo;
    private Boolean isPacked = false;
    private Boolean isFirmware = false;
    private Boolean isWipedClean = false;
    private Boolean isRejected = false;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "back_id")
    @NonNull
    private CameraBack cameraBack;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "body_id")
    @NonNull
    private CameraBody cameraBody;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "lens_id")
    @NonNull
    private CameraLens cameraLens;
}
