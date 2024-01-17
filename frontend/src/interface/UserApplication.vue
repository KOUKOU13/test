<script setup lang="ts">

export type UserApplication = {
    id: number,
    userId: number,
    rideId: number,
    passengerCount: number,
    message: string,
    isAccepted: boolean,
    isRejected: boolean,
    rejectionMessage: string
}

</script>
