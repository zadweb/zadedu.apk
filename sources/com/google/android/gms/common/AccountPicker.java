package com.google.android.gms.common;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.plus.PlusShare;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
public final class AccountPicker {
    private AccountPicker() {
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
    public static class AccountChooserOptions {
        private Account zza;
        private ArrayList<Account> zzb;
        private ArrayList<String> zzc;
        private boolean zzd;
        private String zze;
        private Bundle zzf;
        private boolean zzg;
        private int zzh;
        private String zzi;
        private boolean zzj;
        private zza zzk;
        private String zzl;
        private boolean zzm;

        /* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
        public static class zza {
        }

        /* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
        public static class Builder {
            private Account zza;
            private ArrayList<Account> zzb;
            private ArrayList<String> zzc;
            private boolean zzd = false;
            private String zze;
            private Bundle zzf;
            private boolean zzg = false;
            private int zzh = 0;
            private boolean zzi = false;

            public Builder setSelectedAccount(Account account) {
                this.zza = account;
                return this;
            }

            public Builder setAllowableAccounts(List<Account> list) {
                this.zzb = list == null ? null : new ArrayList<>(list);
                return this;
            }

            public Builder setAllowableAccountsTypes(List<String> list) {
                this.zzc = list == null ? null : new ArrayList<>(list);
                return this;
            }

            public Builder setAlwaysShowAccountPicker(boolean z) {
                this.zzd = z;
                return this;
            }

            public Builder setTitleOverrideText(String str) {
                this.zze = str;
                return this;
            }

            public Builder setOptionsForAddingAccount(Bundle bundle) {
                this.zzf = bundle;
                return this;
            }

            public AccountChooserOptions build() {
                Preconditions.checkArgument(true, "We only support hostedDomain filter for account chip styled account picker");
                Preconditions.checkArgument(true, "Consent is only valid for account chip styled account picker");
                AccountChooserOptions accountChooserOptions = new AccountChooserOptions();
                accountChooserOptions.zzc = this.zzc;
                accountChooserOptions.zzb = this.zzb;
                accountChooserOptions.zzd = this.zzd;
                accountChooserOptions.zzk = null;
                accountChooserOptions.zzi = null;
                accountChooserOptions.zzf = this.zzf;
                accountChooserOptions.zza = this.zza;
                accountChooserOptions.zzg = false;
                accountChooserOptions.zzl = null;
                accountChooserOptions.zzh = 0;
                accountChooserOptions.zze = this.zze;
                accountChooserOptions.zzj = false;
                accountChooserOptions.zzm = false;
                return accountChooserOptions;
            }
        }
    }

    @Deprecated
    public static Intent newChooseAccountIntent(Account account, ArrayList<Account> arrayList, String[] strArr, boolean z, String str, String str2, String[] strArr2, Bundle bundle) {
        Intent intent = new Intent();
        Preconditions.checkArgument(true, "We only support hostedDomain filter for account chip styled account picker");
        intent.setAction("com.google.android.gms.common.account.CHOOSE_ACCOUNT");
        intent.setPackage("com.google.android.gms");
        intent.putExtra("allowableAccounts", arrayList);
        intent.putExtra("allowableAccountTypes", strArr);
        intent.putExtra("addAccountOptions", bundle);
        intent.putExtra("selectedAccount", account);
        intent.putExtra("alwaysPromptForAccount", z);
        intent.putExtra("descriptionTextOverride", str);
        intent.putExtra("authTokenType", str2);
        intent.putExtra("addAccountRequiredFeatures", strArr2);
        intent.putExtra("setGmsCoreAccount", false);
        intent.putExtra("overrideTheme", 0);
        intent.putExtra("overrideCustomTheme", 0);
        intent.putExtra("hostedDomainFilter", (String) null);
        return intent;
    }

    public static Intent newChooseAccountIntent(AccountChooserOptions accountChooserOptions) {
        Intent intent = new Intent();
        int i = 0;
        if (!accountChooserOptions.zzj) {
            Preconditions.checkArgument(accountChooserOptions.zzi == null, "We only support hostedDomain filter for account chip styled account picker");
            Preconditions.checkArgument(accountChooserOptions.zzk == null, "Consent is only valid for account chip styled account picker");
        }
        intent.setAction(accountChooserOptions.zzj ? "com.google.android.gms.common.account.CHOOSE_ACCOUNT_USERTILE" : "com.google.android.gms.common.account.CHOOSE_ACCOUNT");
        intent.setPackage("com.google.android.gms");
        intent.putExtra("allowableAccounts", accountChooserOptions.zzb);
        if (accountChooserOptions.zzc != null) {
            intent.putExtra("allowableAccountTypes", (String[]) accountChooserOptions.zzc.toArray(new String[0]));
        }
        intent.putExtra("addAccountOptions", accountChooserOptions.zzf);
        intent.putExtra("selectedAccount", accountChooserOptions.zza);
        intent.putExtra("alwaysPromptForAccount", accountChooserOptions.zzd);
        intent.putExtra("descriptionTextOverride", accountChooserOptions.zze);
        intent.putExtra("setGmsCoreAccount", accountChooserOptions.zzg);
        intent.putExtra("realClientPackage", accountChooserOptions.zzl);
        intent.putExtra("overrideTheme", accountChooserOptions.zzh);
        if (accountChooserOptions.zzj) {
            i = 2;
        }
        intent.putExtra("overrideCustomTheme", i);
        intent.putExtra("hostedDomainFilter", accountChooserOptions.zzi);
        Bundle bundle = new Bundle();
        if (accountChooserOptions.zzj && !TextUtils.isEmpty(accountChooserOptions.zze)) {
            bundle.putString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, accountChooserOptions.zze);
        }
        if (accountChooserOptions.zzk != null) {
            bundle.putBoolean("should_show_consent", true);
            bundle.putString("privacy_policy_url", null);
            bundle.putString("terms_of_service_url", null);
        }
        if (accountChooserOptions.zzm) {
            bundle.putBoolean("exclude_add_account", true);
        }
        if (!bundle.isEmpty()) {
            intent.putExtra("first_party_options_bundle", bundle);
        }
        return intent;
    }
}
