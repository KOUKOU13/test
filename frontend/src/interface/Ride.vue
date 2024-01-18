<script setup lang="ts">

export type Ride = {
    id: number,
    startId: number,
    destId: number,
    driverId: number,
    passengerLimit: number,
    price: number,
    startTimestamp: number,
    description: string,
    isSmokingAllowed: boolean,
    petTransportAllowed: boolean,
    generation: number
}

</script>
