package com.game.scene.entity;

import com.game.utils.JsonUtil;
import org.hibernate.annotations.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.List;

@Entity(name = "map")
@Table(appliesTo = "map")
public class MapEnt {

    @Id
    private int mapId;
    private String mapName;
    @Lob
    private byte[] existNpcidsData;
    @Lob
    private byte[] existMonsterIdsData;
    @Lob
    private byte[] mapNearByIdsData;

    private transient List<Long> existNpcIds;

    private transient List<Long> existMonsterIds;

    private transient List<Integer> mapNearByIds;

    public MapEnt() {
    }

    public MapEnt(int mapId, String mapName,
                  List<Long> existMonsterIds,
                  List<Long> existNpcIds,
                  List<Integer> mapNearByIds){
        this.mapId = mapId;
        this.mapName = mapName;
        this.existMonsterIds = existMonsterIds;
        this.existNpcIds = existNpcIds;
        this.mapNearByIds = mapNearByIds;
        this.doSerialize();
    }

    public void doSerialize(){
        this.existNpcidsData = JsonUtil.object2bytes(existNpcIds);
        this.existMonsterIdsData = JsonUtil.object2bytes(existMonsterIds);
        this.mapNearByIdsData = JsonUtil.object2bytes(mapNearByIds);
    }

    public void doDeserialize(){
        this.existNpcIds = JsonUtil.bytes2Object(existNpcidsData, List.class);
        this.existMonsterIds = JsonUtil.bytes2Object(existMonsterIdsData, List.class);
        this.mapNearByIds = JsonUtil.bytes2Object(mapNearByIdsData, List.class);
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public List<Long> getExistNpcIds() {
        return existNpcIds;
    }

    public void setExistNpcIds(List<Long> existNpcIds) {
        this.existNpcIds = existNpcIds;
    }

    public List<Long> getExistMonsterIds() {
        return existMonsterIds;
    }

    public void setExistMonsterIds(List<Long> existMonsterIds) {
        this.existMonsterIds = existMonsterIds;
    }

    public byte[] getExistNpcidsData() {
        return existNpcidsData;
    }

    public void setExistNpcidsData(byte[] existNpcidsData) {
        this.existNpcidsData = existNpcidsData;
    }

    public byte[] getExistMonsterIdsData() {
        return existMonsterIdsData;
    }

    public void setExistMonsterIdsData(byte[] existMonsterIdsData) {
        this.existMonsterIdsData = existMonsterIdsData;
    }

    public List<Integer> getMapNearByIds() {
        return mapNearByIds;
    }

    public void setMapNearByIds(List<Integer> mapNearByIds) {
        this.mapNearByIds = mapNearByIds;
    }

    public byte[] getMapNearByIdsData() {
        return mapNearByIdsData;
    }

    public void setMapNearByIdsData(byte[] mapNearByIdsData) {
        this.mapNearByIdsData = mapNearByIdsData;
    }
}
