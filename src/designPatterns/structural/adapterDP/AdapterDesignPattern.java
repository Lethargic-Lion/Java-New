package designPatterns.structural.adapterDP;

interface UsbC {
    void chargeWithUsbC();
}

class AndroidPhone implements UsbC {
    @Override
    public void chargeWithUsbC() {
        System.out.println("Charging Android phone with USB-C");
    }
}

interface Lightning {
    void chargeWithLightning();
}

class IPhone implements Lightning {
    @Override
    public void chargeWithLightning() {
        System.out.println("Charging iPhone with Lightning cable");
    }
}

interface UsbA {
    void chargeWithUsbA();
}

class OldPhone implements UsbA {
    @Override
    public void chargeWithUsbA() {
        System.out.println("Charging old phone with USB-A cable");
    }
}

class LightningToUsbCAdapter implements UsbC {
    private final Lightning lightningDevice;

    public LightningToUsbCAdapter(Lightning lightningDevice) {
        this.lightningDevice = lightningDevice;
    }

    @Override
    public void chargeWithUsbC() {
        System.out.print("Adapter converting USB-C to Lightning. ");
        lightningDevice.chargeWithLightning();
    }
}

class UsbAtoUsbCAdapter implements UsbC {
    private final UsbA usbADevice;

    public UsbAtoUsbCAdapter(UsbA usbADevice) {
        this.usbADevice = usbADevice;
    }

    @Override
    public void chargeWithUsbC() {
        System.out.print("Adapter converting USB-C to USB-A. ");
        usbADevice.chargeWithUsbA();
    }
}

class ChargingStation {
    public static void chargeDevice(UsbC device) {
        device.chargeWithUsbC();
    }
}

public class AdapterDesignPattern {
    static void main() {
        // charging an Android phone directly with USB-C from ChargingStation
        UsbC androidPhone = new AndroidPhone();
        ChargingStation.chargeDevice(androidPhone);


        // charging an old phone with USB-A using adapter to USB-C from ChargingStation
        OldPhone oldPhone = new OldPhone();
        UsbC aToCAdapter = new UsbAtoUsbCAdapter(oldPhone);
        ChargingStation.chargeDevice(aToCAdapter);

        // charging an iPhone with Lightning using adapter to USB-C from ChargingStation
        Lightning iPhone = new IPhone();
        UsbC lightningToUsbCAdapter = new LightningToUsbCAdapter(iPhone);
        ChargingStation.chargeDevice(lightningToUsbCAdapter);
    }
}
