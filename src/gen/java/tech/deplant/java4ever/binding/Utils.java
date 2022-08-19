package tech.deplant.java4ever.binding;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Map;
import java.util.Optional;
import lombok.*;
import java.util.stream.*;
import java.util.Arrays;

/**
 *  <h1>utils</h1>
 *  Contains methods of "utils" module.

 *  Misc utility Functions.
 *  @version EVER-SDK 1.37.0
 */
public class Utils {

    public interface AddressStringFormat {

        public static final AccountId ACCOUNTID = new AccountId();


    /**
    * 

    */
    public record AccountId() implements AddressStringFormat {
                               @JsonProperty("type")
                               public String type() { return getClass().getSimpleName(); }
                           }

        public static final Hex HEX = new Hex();


    /**
    * 

    */
    public record Hex() implements AddressStringFormat {
                               @JsonProperty("type")
                               public String type() { return getClass().getSimpleName(); }
                           }


    /**
    * 
    * @param url 
    * @param test 
    * @param bounce 
    */
    public record Base64(@NonNull Boolean url, @NonNull Boolean test, @NonNull Boolean bounce) implements AddressStringFormat {
                               @JsonProperty("type")
                               public String type() { return getClass().getSimpleName(); }
                           }
}
    public enum AccountAddressType {
        
        
        AccountId,

        
        Hex,

        
        Base64
    }

    /**
    * 
    * @param address Account address in any TON format.
    * @param outputFormat Specify the format to convert to.
    */
    public record ParamsOfConvertAddress(@NonNull String address, @NonNull AddressStringFormat outputFormat) {}

    /**
    * 
    * @param address Address in the specified format
    */
    public record ResultOfConvertAddress(@NonNull String address) {}

    /**
    * 
    * @param address Account address in any TON format.
    */
    public record ParamsOfGetAddressType(@NonNull String address) {}

    /**
    * 
    * @param addressType Account address type.
    */
    public record ResultOfGetAddressType(@NonNull AccountAddressType addressType) {}

    /**
    * 
    * @param account 
    * @param period 
    */
    public record ParamsOfCalcStorageFee(@NonNull String account, @NonNull Number period) {}

    /**
    * 
    * @param fee 
    */
    public record ResultOfCalcStorageFee(@NonNull String fee) {}

    /**
    * 
    * @param uncompressed Uncompressed data. Must be encoded as base64.
    * @param level Compression level, from 1 to 21. Where: 1 - lowest compression level (fastest compression); 21 - highest compression level (slowest compression). If level is omitted, the default compression level is used (currently `3`).
    */
    public record ParamsOfCompressZstd(@NonNull String uncompressed, Number level) {}

    /**
    * 
    * @param compressed Compressed data. Must be encoded as base64.
    */
    public record ResultOfCompressZstd(@NonNull String compressed) {}

    /**
    * 
    * @param compressed Compressed data. Must be encoded as base64.
    */
    public record ParamsOfDecompressZstd(@NonNull String compressed) {}

    /**
    * 
    * @param decompressed Decompressed data. Must be encoded as base64.
    */
    public record ResultOfDecompressZstd(@NonNull String decompressed) {}
    /**
    * <h2>utils.convert_address</h2>
    * Converts address from any TON format to any TON format
    * @param address Account address in any TON format. 
    * @param outputFormat Specify the format to convert to. 
    * @return {@link tech.deplant.java4ever.binding.Utils.ResultOfConvertAddress}
    */
    public static ResultOfConvertAddress convertAddress(@NonNull Context ctx, @NonNull String address, @NonNull AddressStringFormat outputFormat)  throws JsonProcessingException {
        return ctx.call("utils.convert_address", new ParamsOfConvertAddress(address, outputFormat), ResultOfConvertAddress.class);
    }

    /**
    * <h2>utils.get_address_type</h2>
    * Validates and returns the type of any TON address. Address types are the following<p>`0:919db8e740d50bf349df2eea03fa30c385d846b991ff5542e67098ee833fc7f7` - standard TON address mostcommonly used in all cases. Also called as hex address`919db8e740d50bf349df2eea03fa30c385d846b991ff5542e67098ee833fc7f7` - account ID. A part of fulladdress. Identifies account inside particular workchain`EQCRnbjnQNUL80nfLuoD+jDDhdhGuZH/VULmcJjugz/H9wam` - base64 address. Also called "user-friendly".Was used at the beginning of TON. Now it is supported for compatibility
    * @param address Account address in any TON format. 
    * @return {@link tech.deplant.java4ever.binding.Utils.ResultOfGetAddressType}
    */
    public static ResultOfGetAddressType getAddressType(@NonNull Context ctx, @NonNull String address)  throws JsonProcessingException {
        return ctx.call("utils.get_address_type", new ParamsOfGetAddressType(address), ResultOfGetAddressType.class);
    }

    /**
    * <h2>utils.calc_storage_fee</h2>
    * Calculates storage fee for an account over a specified time period
    * @param account  
    * @param period  
    * @return {@link tech.deplant.java4ever.binding.Utils.ResultOfCalcStorageFee}
    */
    public static ResultOfCalcStorageFee calcStorageFee(@NonNull Context ctx, @NonNull String account, @NonNull Number period)  throws JsonProcessingException {
        return ctx.call("utils.calc_storage_fee", new ParamsOfCalcStorageFee(account, period), ResultOfCalcStorageFee.class);
    }

    /**
    * <h2>utils.compress_zstd</h2>
    * Compresses data using Zstandard algorithm
    * @param uncompressed Uncompressed data. Must be encoded as base64.
    * @param level Compression level, from 1 to 21. Where: 1 - lowest compression level (fastest compression); 21 - highest compression level (slowest compression). If level is omitted, the default compression level is used (currently `3`). 
    * @return {@link tech.deplant.java4ever.binding.Utils.ResultOfCompressZstd}
    */
    public static ResultOfCompressZstd compressZstd(@NonNull Context ctx, @NonNull String uncompressed,  Number level)  throws JsonProcessingException {
        return ctx.call("utils.compress_zstd", new ParamsOfCompressZstd(uncompressed, level), ResultOfCompressZstd.class);
    }

    /**
    * <h2>utils.decompress_zstd</h2>
    * Decompresses data using Zstandard algorithm
    * @param compressed Compressed data. Must be encoded as base64.
    * @return {@link tech.deplant.java4ever.binding.Utils.ResultOfDecompressZstd}
    */
    public static ResultOfDecompressZstd decompressZstd(@NonNull Context ctx, @NonNull String compressed)  throws JsonProcessingException {
        return ctx.call("utils.decompress_zstd", new ParamsOfDecompressZstd(compressed), ResultOfDecompressZstd.class);
    }

}
